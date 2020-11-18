package sep3tier2.tier2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sep3tier2.tier2.models.*;
import sep3tier2.tier2.networking.SocketClient;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
//        seedUsers();
//        seedPosts();
    }

    @Override
    public boolean addUser(User user) {
        System.out.println("Adding user " + user.getEmail());
        return socketClient.addUser(user);
    }

    @Override
    public String login(String email, String password)
    {
        if (email != null && password != null)
            return socketClient.login(email, password);
//        for (User user : users) {
//            if (user.getEmail().equals(email)
//                    && user.getPassword().equals(password)) {
//                System.out.println("Logging in user " + user.getId());
//                return new UserShortVersion(user.getId(), user.getName(), user.getAccountType());
//            }
//        }
//        System.out.println("No user found for given login credentials");
//        return null;
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

//    private void seedUsers() {
//        if (users.size() != 0)
//            return;
//        List<Integer> likedPostsSeed = new ArrayList<>();
//        likedPostsSeed.add(1); likedPostsSeed.add(2); likedPostsSeed.add(3);
//
//        List<Integer> postsSeed = new ArrayList<>();
//        postsSeed.add(1); postsSeed.add(2); postsSeed.add(3);
//
//        User user = new User(0, "barry.allen@flash.com", "flash", "RegularUser",
//                "My name is Barry Allen and I am the fastest man on the Earth", "Barry Allen", "Central City", new Address("yes", "1"));
//        users.add(user);
//    }

//    private void seedPosts() {
//        if (posts.size() != 0)
//            return;
//        UserShortVersion userShortVersion = new UserShortVersion(0, "Barry", "Allen");
//        LocalDate now = LocalDate.now();
//        Post post = new Post(0, "My Post", "Content of my post", userShortVersion, now);
//        posts.add(post);
//    }
}
