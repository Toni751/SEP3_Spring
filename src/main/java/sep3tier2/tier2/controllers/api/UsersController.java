package sep3tier2.tier2.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sep3tier2.tier2.models.Post;
import sep3tier2.tier2.models.User;
import sep3tier2.tier2.models.UserShortVersion;
import sep3tier2.tier2.services.UserService;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController
{
    @Autowired
    UserService userService;

    @CrossOrigin(origins = "*")
    @PostMapping
    public boolean addUser(@RequestBody User user)
    {
        System.out.println("Controller adding user");
        boolean response = userService.addUser(user);
        if (!response)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User could not be added");
        return true;
    }

    @CrossOrigin(origins = "*")
    @GetMapping()
    public @ResponseBody String loginUser(@RequestParam("email") String email,
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
        System.out.println("Controller get user by id = " + id);
        return userService.getUserById(id);
    }


}
