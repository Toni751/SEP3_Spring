package sep3tier2.tier2.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sep3tier2.tier2.models.*;
import sep3tier2.tier2.networking.user.SocketUser;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SocketUser socketUser;

    public UserServiceImpl() {
    }

    @Override
    public boolean addUser(User user) {
        System.out.println("Adding user " + user.getEmail());
        return socketUser.addUser(user);
    }

    @Override
    public UserShortVersion login(String email, String password) {
        if (email != null && password != null)
            return socketUser.login(email, password);
        return null;
    }

    @Override
    public void editUser(User user) throws Exception {
        //maybe add some validation here?
        try {
            socketUser.editUser(user);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public User getUserById(int senderId, int receiverId) {
        return socketUser.getUserById(senderId, receiverId);
    }

    @Override
    public void deleteUser(int id) throws Exception {
        try {
            socketUser.deleteUser(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public int postUserAction(UserAction userAction) throws Exception {

        switch (userAction.getActionType()) {
            case USER_FRIEND_REQUEST_SEND:
            case USER_FRIEND_REQUEST_RESPONSE:
            case USER_FRIEND_REMOVE:
            case USER_SHARE_TRAININGS:
            case USER_SHARE_DIETS:
            case USER_FOLLOW_PAGE:
            case USER_REPORT:
                if (!(userAction.getValue() instanceof Boolean))
                    throw new Exception("Sent request value has to be a boolean");
                break;
            case USER_RATE_PAGE:
                if (!(userAction.getValue() instanceof Integer))
                    throw new Exception("Sent request value has to be an integer");
                Integer rating = (Integer) userAction.getValue();
                if (rating < 1 || rating > 5)
                    throw new Exception("Page rating has to be an integer between 1 and 5");
                break;
            default:
                throw new Exception("Invalid action request");
        }

        try {
            return socketUser.postUserAction(userAction);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteNotification(int notificationId) throws Exception {
        if (notificationId < 1)
            throw new Exception("Invalid delete notification id");
        try {
            socketUser.deleteNotification(notificationId);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<SearchBarUser> getUsersByFilter(String filter) throws Exception{
        if (filter == null || filter.equals(""))
            throw new Exception("Invalid filter string");

        try {
            return socketUser.getUsersByFilter(filter);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<UserShortVersion> getGymsByCity(String city) throws Exception {
        if (city == null || city.equals(""))
            throw new Exception("Invalid city name");

        try {
            return socketUser.getGymsByCity(city);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Notification> getNotificationsForUser(int id) throws Exception {
        if (id <= 0)
            throw new Exception("Invalid user id");

        try {
            return socketUser.getNotificationsForUser(id);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<UserShortVersionWithStatus> getFriendListForUser(int userId, int senderId, int offset) throws Exception {
        if (userId <= 0 || senderId <= 0 || offset < 0)
            throw new Exception("Invalid request parameters");

        try {
            return socketUser.getFriendListForUser(userId, senderId, offset) ;}
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void incrementUserScore(int userId, int amount) throws Exception {
        if(userId <= 0 || amount <= 0 )
            throw new Exception("Invalid user/amount");
        try {
            socketUser.incrementUserScore(userId, amount);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<UserShortVersion> getOnlineFriendsForUser(int id) throws Exception {
        if(id <= 0)
            throw new Exception("Invalid user id " + id);
        try {
            return socketUser.getOnlineFriendsForUser(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Integer> userLogInOrOut(int userId, boolean isLogout) {
        if(userId > 0)
             return socketUser.userLogInOrOut(userId, isLogout);

        return null;
    }

    @Override
    public UserShortVersion getUserShortVersionById(int userId) throws Exception{
        if(userId <= 0)
            throw new Exception("Invalid user id " + userId);

        return socketUser.getUserShortVersionById(userId);
    }
}
