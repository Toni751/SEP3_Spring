package sep3tier2.tier2.services.user;

import sep3tier2.tier2.models.*;

import java.util.List;

public interface UserService
{
    boolean addUser(User user);
    UserShortVersion login (String email, String password);
    void editUser (User user) throws Exception;
    User getUserById(int senderId, int receiverId);
    void deleteUser(int id) throws Exception;
    int postUserAction(UserAction userAction) throws Exception;
    void deleteNotification(int notificationId) throws Exception;
    List<SearchBarUser> getUsersByFilter(String filter) throws Exception;
    List<UserShortVersion> getGymsByCity(String city) throws Exception;
    List<Notification> getNotificationsForUser(int id) throws Exception;
    List<UserShortVersion> getFriendListForUser(int userId, int senderId, int offset) throws Exception;
}
