package sep3tier2.tier2.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.server.ResponseStatusException;
import sep3tier2.tier2.models.*;
import sep3tier2.tier2.services.user.UserService;

import java.util.List;

//@SessionScope
@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserService userService;

    @CrossOrigin(origins = "*")
    @PostMapping
    public HttpStatus addUser(@RequestBody User user) {
        System.out.println(this.hashCode());
        System.out.println(this.toString());
        System.out.println("Controller adding user");
        boolean response = userService.addUser(user);
        if (!response)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User could not be added");
        System.out.println("Returning successful");
        return HttpStatus.CREATED;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/login")
    public @ResponseBody
    UserShortVersion loginUser(@RequestParam("email") String email,
                               @RequestParam("password") String password) {
        System.out.println(this.hashCode());
        System.out.println(this.toString());
        System.out.println("Controller login user called with " + email + " " + password);
        UserShortVersion user = userService.login(email, password);
        if (user == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
        return user;
    }

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

    @CrossOrigin(origins = "*")
    @GetMapping()
    public @ResponseBody
    User getUserById(@RequestParam("senderId") int senderId, @RequestParam("receiverId") int receiverId) {
        System.out.println("Controller get user " + receiverId + " by " + senderId);
        return userService.getUserById(senderId, receiverId);
    }

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

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}/score")
    public HttpStatus incrementUserScoreByAmount(@PathVariable int id, @RequestBody int amount)
    {
        System.out.println("Controller incrementing user " + id + " score by amount " + amount);
        try {
            userService.incrementUserScore(id, amount);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

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
