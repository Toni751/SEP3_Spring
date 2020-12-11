package sep3tier2.tier2.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.server.ResponseStatusException;
import sep3tier2.tier2.models.*;
import sep3tier2.tier2.services.user.UserService;

import java.util.List;

/**
 * Rest API Controller for user-related requests at the endpoint "/users"
 * @version 1.0
 * @author Group1
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserService userService;

    /**
     * Adds a given user
     * @param user the user to be added
     * @return Ok if the action was successful, Bad_Request otherwise
     */
    @CrossOrigin(origins = "*")
    @PostMapping
    public HttpStatus addUser(@RequestBody User user) {
        System.out.println("Controller adding user");
        boolean response = userService.addUser(user);
        if (!response)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User could not be added");
        System.out.println("Returning successful");
        return HttpStatus.CREATED;
    }

    /**
     * Logs in a user with the specified credentials
     * @param email the value of the email
     * @param password the value of the password
     * @return the user short version instance with the corresponding credentials, if any
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/login")
    public @ResponseBody
    UserShortVersion loginUser(@RequestParam("email") String email, @RequestParam("password") String password)
    {
        System.out.println(this.hashCode());
        System.out.println(this.toString());
        System.out.println("Controller login user called with " + email + " " + password);
        UserShortVersion user = userService.login(email, password);
        if (user == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
        return user;
    }

    /**
     * Edits a user at a given id
     * @param id the id of the user
     * @param user the new value for the user
     * @return Ok if the action was successful, Bad_Request otherwise
     */
    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public HttpStatus editUser(@PathVariable int id, @RequestBody User user) {
        System.out.println("Controller editing user " + id);
        try {
            userService.editUser(user);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Deletes a user at a given id
     * @param id the id of the user to be deleted
     * @return Ok if the action was successful, Bad_Request otherwise
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public HttpStatus deleteUser(@PathVariable int id) {
        System.out.println("Controller deleting user " + id);
        try {
            userService.deleteUser(id);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Returns a user by its id, or null if it doesn't exist
     * @param senderId the id of the user who sent the request
     * @param receiverId the id of the targeted user
     * @return the user's details, including his status in rapport with the sender(if they are friends or not, etc)
     */
    @CrossOrigin(origins = "*")
    @GetMapping()
    public @ResponseBody
    User getUserById(@RequestParam("senderId") int senderId, @RequestParam("receiverId") int receiverId) {
        System.out.println("Controller get user " + receiverId + " by " + senderId);
        return userService.getUserById(senderId, receiverId);
    }

    /**
     * Returns a user short version of the user with the given id
     * @param id the id of the user
     * @return a user short version representation of the user
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/{id}/short")
    public @ResponseBody UserShortVersion getUserShortVersionById(@PathVariable int id) {
        System.out.println("Controller getting user short version by id " + id);
        try {
            return userService.getUserShortVersionById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Creates a new user action
     * @param userAction the user action to be created
     * @return Ok if the action was successful, Bad_Request otherwise
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/actions")
    public HttpStatus postUserAction(@RequestBody UserAction userAction) {
        System.out.println("Controller user action " + userAction.getActionType() + " with value " + userAction.getValue());
        try {
            userService.postUserAction(userAction);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Returns the friend list or common friends between 2 user
     * @param id the id of the target user
     * @param senderId the id of the user who sent the request
     * @param offset the number of users in the list to be skipped
     * @return a list with the target user's relevant friends
     */
    @CrossOrigin(origins = "*")
    @GetMapping("{id}/friends")
    public List<UserShortVersionWithStatus> getFriendListForUser(@PathVariable int id,
                @RequestParam("senderId") int senderId, @RequestParam("offset") int offset) {
        System.out.println("Controller get friend list for user " + id + " by user " + senderId + " with offset " + offset);
        try {
            return userService.getFriendListForUser(id, senderId, offset);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Marks a notification with a given id as read, deleting it
     * @param id the id of the notification
     * @return Ok if the action was successful, Bad_Request otherwise
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("/notifications/{id}")
    public HttpStatus markNotificationAsRead(@PathVariable int id) {
        System.out.println("Controller deleting notification " + id);
        try {
            userService.deleteNotification(id);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Returns all the gyms in a given city
     * @param city the city name
     * @return the list with gyms, as user short version instances
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/pages")
    public List<UserShortVersion> getGymsInCity(@RequestParam("city") String city) {
        System.out.println("Controller getting gyms in city " + city);
        try {
            return userService.getGymsByCity(city);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Retrieves all the notifications belonging to a given user
     * @param id the id of the user
     * @return a list with all the user's unread notifications
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/{id}/notifications")
    public List<Notification> getNotificationsForUser(@PathVariable int id) {
        System.out.println("Controller getting notifications for user " + id);
        try {
            return userService.getNotificationsForUser(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Increments a user's fitness score by a given amount
     * @param id the id of the user
     * @param scoreToAdd the value to be added
     * @return Ok if the action was successful, Bad_Request otherwise
     */
    @CrossOrigin(origins = "*")
    @PutMapping("/{id}/score")
    public HttpStatus incrementUserScoreByAmount(@PathVariable int id, @RequestParam("scoreToAdd") int scoreToAdd)
    {
        System.out.println("Controller incrementing user " + id + " score by amount " + scoreToAdd);
        try {
            userService.incrementUserScore(id, scoreToAdd);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Retrieves a list with a user's currently online friends
     * @param id the id of the user
     * @return a list with the user's online friends
     */
    @CrossOrigin(origins = "*")
    @GetMapping("{id}/online_friends")
    public List<UserShortVersion> getOnlineFriendsForUser(@PathVariable int id) {
        System.out.println("Controller get online friends list for user " + id);
        try {
            return userService.getOnlineFriendsForUser(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
