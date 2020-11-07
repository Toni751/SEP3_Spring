package sep3tier2.tier2.services;

import org.springframework.stereotype.Service;
import sep3tier2.tier2.models.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    private List<User> users;

    public UserServiceImpl() {
        users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        System.out.println("Adding user " + user.getEmail());
        users.add(user);
    }

    @Override
    public User login(String email, String password)
    {
        for (User user : users) {
            if (user.getEmail().equals(email)
                    && user.getPassword().equals(password)) {
                System.out.println("Logging in user " + user.getId());
                return user;
            }
        }
        System.out.println("No user found for given login credentials");
        return null;
    }
}
