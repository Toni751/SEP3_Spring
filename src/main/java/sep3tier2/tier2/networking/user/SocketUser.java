package sep3tier2.tier2.networking.user;

import sep3tier2.tier2.models.SearchBarUser;
import sep3tier2.tier2.models.User;
import sep3tier2.tier2.models.UserAction;
import sep3tier2.tier2.models.UserShortVersion;

import java.util.List;

public interface SocketUser
{
    boolean addUser(User user);
    UserShortVersion login (String email, String password);
    void editUser (User user) throws Exception;
    User getUserById(int senderId, int receiverId);
    void deleteUser(int id) throws Exception;
    int postUserAction(UserAction userAction) throws Exception;
    void deleteNotification(int notificationId) throws Exception;
    List<SearchBarUser> getUsersByFilter(String filter);
}
