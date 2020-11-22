package sep3tier2.tier2.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import sep3tier2.tier2.models.PostShortVersion;
import sep3tier2.tier2.models.UserShortVersion;
import sep3tier2.tier2.services.admin.AdminService;

import java.util.List;

@CrossOrigin(origins = "*")
@SessionScope
@RestController
@RequestMapping("/admin")
public class AdministratorController
{
    @Autowired
    AdminService adminService;

    @GetMapping("/users")
    public @ResponseBody List<UserShortVersion> getReportedUsers(@RequestParam("limit") int limit, @RequestParam("offset") int offset)
    {
        System.out.println("Admin Controller getting " + limit + " reported users with offset " + offset);
        return adminService.getUsers(limit, offset);
    }

    @GetMapping("/posts")
    public @ResponseBody List<PostShortVersion> getReportedPosts(@RequestParam("limit") int limit, @RequestParam("offset") int offset)
    {
        System.out.println("Admin Controller getting " + limit + " reported posts with offset " + offset);
        return adminService.getPosts(limit, offset);
    }
}
