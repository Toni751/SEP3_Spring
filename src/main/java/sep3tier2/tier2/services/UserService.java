package sep3tier2.tier2.services;

import sep3tier2.tier2.models.Post;
import sep3tier2.tier2.models.User;
import sep3tier2.tier2.models.UserShortVersion;

import java.util.List;

public interface UserService
{
    boolean addUser(User user);
    String login (String email, String password);
    List<Post> getLatestPostsForUser(int id, int offset);

    User getUserById(int id);
}
