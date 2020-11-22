package sep3tier2.tier2.services.user;

import sep3tier2.tier2.models.Post;
import sep3tier2.tier2.models.User;
import sep3tier2.tier2.models.UserAction;
import sep3tier2.tier2.models.UserShortVersion;

import java.util.List;

public interface UserService
{
    boolean addUser(User user);
    UserShortVersion login (String email, String password);
    void editUser (User user) throws Exception;
    User getUserById(int senderId, int receiverId);
    void deleteUser(int id) throws Exception;
    void postUserAction(UserAction userAction) throws Exception;
}
