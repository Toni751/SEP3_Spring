package sep3tier2.tier2.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.server.ResponseStatusException;
import sep3tier2.tier2.models.Post;
import sep3tier2.tier2.models.User;
import sep3tier2.tier2.models.UserAction;
import sep3tier2.tier2.models.UserShortVersion;
import sep3tier2.tier2.services.user.UserService;

import java.util.List;

@SessionScope
@RestController
@RequestMapping("/users")
public class UsersController
{
    @Autowired
    UserService userService;

    @CrossOrigin(origins = "*")
    @PostMapping
    public HttpStatus addUser(@RequestBody User user)
    {
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
    public @ResponseBody UserShortVersion loginUser(@RequestParam("email") String email,
                               @RequestParam("password") String password)
    {
        System.out.println(this.hashCode());
        System.out.println(this.toString());
        System.out.println("Controller login user called with " + email + " " + password);
        return userService.login(email, password);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public HttpStatus editUser(@PathVariable int id, @RequestBody User user)
    {
        System.out.println("Controller editing user " + id);
        try{
            userService.editUser(user);
            return HttpStatus.OK;
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public HttpStatus deleteUser(@PathVariable int id)
    {
        System.out.println("Controller deleting user " + id);
        try{
            userService.deleteUser(id);
            return HttpStatus.OK;
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

//    @CrossOrigin(origins = "*")
//    @GetMapping("/wall")
//    public @ResponseBody List<Post> retrieveRegularUserWall(@RequestParam("id") int id, @RequestParam("offset") int offset)
//    {
//        System.out.println("Controller get latest posts for a regular user");
//        return userService.getLatestPostsForUser(id, offset);
//    }

    @CrossOrigin(origins = "*")
    @GetMapping()
    public @ResponseBody User getUserById(@RequestParam("senderId") int senderId, @RequestParam("receiverId") int receiverId)
    {
        System.out.println("Controller get user " + receiverId + " by " + senderId);
        return userService.getUserById(senderId, receiverId);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/actions")
    public HttpStatus postUserAction(@RequestBody UserAction userAction)
    {
        System.out.println("Controller user action " + userAction.getActionType() + " with value " + userAction.getValue());
        try{
            userService.postUserAction(userAction);
            return HttpStatus.OK;
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
