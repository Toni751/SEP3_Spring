package sep3tier2.tier2.services.user;

import sep3tier2.tier2.models.user.*;

import java.util.List;

/**
 * The interface for the service class which manages user actions
 * @version 1.0
 * @author Group1
 */
public interface UserService
{
    /**
     * Adds a given user
     * @param user the user to be added
     * @return true if the user was created successfully, false otherwise
     */
    boolean addUser(User user);

    /**
     * Logs in a user with the specified credentials
     * @param email the value of the email
     * @param password the value of the password
     * @return the user short version instance with the corresponding credentials, if any
     */
    UserShortVersion login (String email, String password);

    /**
     * Edits a user at a given id
     * @param user the new value for the user
     */
    void editUser (User user) throws Exception;

    /**
     * Returns a user by its id, or null if it doesn't exist
     * @param senderId the id of the user who sent the request
     * @param receiverId the id of the targeted user
     * @return the user's details, including his status in rapport with the sender(if they are friends or not, etc)
     */
    User getUserById(int senderId, int receiverId);

    /**
     * Deletes a user at a given id
     * @param id the id of the user to be deleted
     */
    void deleteUser(int id) throws Exception;

    /**
     * Creates a new user action
     * @param userAction the user action to be created
     * @return Ok if the action was successful, Bad_Request otherwise
     */
    int postUserAction(UserAction userAction) throws Exception;

    /**
     * Marks a notification with a given id as read, deleting it
     * @param notificationId the id of the notification
     * @return Ok if the action was successful, Bad_Request otherwise
     */
    void deleteNotification(int notificationId) throws Exception;

    /**
     * Filters the users by a given query string and sends the result to the sender
     * @param filter the filter query string
     * @return the list of user whose usernames start with the filter string, if any
     */
    List<SearchBarUser> getUsersByFilter(String filter) throws Exception;

    /**
     * Returns all the gyms in a given city
     * @param city the city name
     * @return the list with gyms, as user short version instances
     */
    List<UserShortVersion> getGymsByCity(String city) throws Exception;

    /**
     * Retrieves all the notifications belonging to a given user
     * @param id the id of the user
     * @return a list with all the user's unread notifications
     */
    List<Notification> getNotificationsForUser(int id) throws Exception;

    /**
     * Returns the friend list or common friends between 2 user
     * @param userId the id of the target user
     * @param senderId the id of the user who sent the request
     * @param offset the number of users in the list to be skipped
     * @return a list with the target user's relevant friends
     */
    List<UserShortVersionWithStatus> getFriendListForUser(int userId, int senderId, int offset) throws Exception;

    /**
     * Increments a user's fitness score by a given amount
     * @param userId the id of the user
     * @param amount the value to be added
     */
    void incrementUserScore(int userId, int amount) throws Exception;

    /**
     * Retrieves a list with a user's currently online friends
     * @param id the id of the user
     * @return a list with the user's online friends
     */
    List<UserShortVersion> getOnlineFriendsForUser(int id) throws Exception;

    /**
     * Logs in or out a given user and notifies all his friends
     * @param userId the id of the user to be logged out
     * @param isLogout true if the actions is log out, false if the action is login
     * @return a list with the user's online friends, so that they can be notified
     */
    List<Integer> userLogInOrOut(int userId, boolean isLogout);

    /**
     * Returns a user short version of the user with the given id
     * @param userId the id of the user
     * @return a user short version representation of the user
     */
    UserShortVersion getUserShortVersionById(int userId) throws Exception;
}
