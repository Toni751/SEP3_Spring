package sep3tier2.tier2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sep3tier2.tier2.models.User;
import sep3tier2.tier2.services.UserService;

@RestController
@RequestMapping("/users")
public class UsersController
{
    @Autowired
    UserService userService;

    @PostMapping
    public void addUser(@RequestBody User user)
    {
        System.out.println("Controller adding user");
        userService.addUser(user);
    }

    @CrossOrigin(origins = "*")
    @GetMapping()
    public @ResponseBody User loginUser(@RequestParam("email") String email,
        @RequestParam("password") String password)
    {
        System.out.println("Controller login user called with " + email + " " + password);
        return userService.login(email, password);
    }
}
