package sep3tier2.tier2.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sep3tier2.tier2.models.Post;
import sep3tier2.tier2.models.User;
import sep3tier2.tier2.models.UserShortVersion;
import sep3tier2.tier2.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController
{
    @Autowired
    UserService userService;

    @CrossOrigin(origins = "*")
    @PostMapping
    public void addUser(@RequestBody User user)
    {
        System.out.println("Controller adding user");
        userService.addUser(user);
    }

    @CrossOrigin(origins = "*")
    @GetMapping()
    public @ResponseBody UserShortVersion loginUser(@RequestParam("email") String email,
                               @RequestParam("password") String password)
    {
        System.out.println("Controller login user called with " + email + " " + password);
        return userService.login(email, password);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/wall")
    public @ResponseBody List<Post> retrieveRegularUserWall(@RequestParam("id") int id, @RequestParam("offset") int offset)
    {
        System.out.println("Controller get latest posts for a regular user");
        return userService.getLatestPostsForUser(id, offset);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public @ResponseBody User getUserById(@PathVariable int id)
    {
        System.out.println("Controller get user by id");
        return userService.getUserById(id);
    }


}
