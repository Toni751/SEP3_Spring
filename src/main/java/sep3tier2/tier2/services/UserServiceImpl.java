package sep3tier2.tier2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sep3tier2.tier2.models.*;
import sep3tier2.tier2.networking.SocketClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    private List<User> users;
    private List<Post> posts;

    @Autowired
    private SocketClient socketClient;

    public UserServiceImpl() {
        users = new ArrayList<>();
        posts = new ArrayList<>();
        seedUsers();
        seedPosts();
    }

    @Override
    public void addUser(User user) {
        System.out.println("Adding user " + user.getEmail());
        users.add(user);
    }

    @Override
    public UserShortVersion login(String email, String password)
    {
        for (User user : users) {
            if (user.getEmail().equals(email)
                    && user.getPassword().equals(password)) {
                System.out.println("Logging in user " + user.getId());
                return new UserShortVersion(user.getId(), user.getName(), user.getAccountType());
            }
        }
        System.out.println("No user found for given login credentials");
        return null;
    }

    @Override
    public List<Post> getLatestPostsForUser(int id, int offset) {
        return posts;
    }

    @Override
    public User getUserById(int id) {
        return users.get(id);
    }

    private void seedUsers() {
        if (users.size() != 0)
            return;
        List<Integer> likedPostsSeed = new ArrayList<>();
        likedPostsSeed.add(1); likedPostsSeed.add(2); likedPostsSeed.add(3);

        List<Integer> postsSeed = new ArrayList<>();
        postsSeed.add(1); postsSeed.add(2); postsSeed.add(3);

        User user = new RegularUser(0, "barry.allen@flash.com", "flash", "RegularUser",
                "My name is Barry Allen and I am the fastest man on the Earth", postsSeed, likedPostsSeed, "Barry Allen", "Central City");
        users.add(user);
    }

    private void seedPosts() {
        if (posts.size() != 0)
            return;
        UserShortVersion userShortVersion = new UserShortVersion(0, "Barry", "Allen");
        LocalDate now = LocalDate.now();
        Post post = new Post(0, "My Post", userShortVersion, now);
        posts.add(post);
    }
}
