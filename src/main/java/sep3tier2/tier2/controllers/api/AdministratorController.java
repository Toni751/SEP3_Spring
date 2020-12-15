package sep3tier2.tier2.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sep3tier2.tier2.models.user.UserShortVersion;
import sep3tier2.tier2.services.admin.AdminService;

import java.util.List;

/**
 * Rest API Controller for administrator-related requests at the endpoint "/admin"
 * @version 1.0
 * @author Group1
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdministratorController {
    @Autowired
    AdminService adminService;

    /**
     * Returns a list of "limit" number of reported users, by skipping the first "offset" number of users
     * @param limit the number of reported users to be returned
     * @param offset the number of reported users to be skipped
     * @return the list of reported users, ordered in descending order by the total number of reports, as user short version objects
     */
    @GetMapping("/users")
    public @ResponseBody List<UserShortVersion> getReportedUsers(@RequestParam("limit") int limit, @RequestParam("offset") int offset) {
        System.out.println("Admin Controller getting " + limit + " reported users with offset " + offset);
        return adminService.getUsers(limit, offset);
    }

    /**
     * Returns a list of "limit" number of reported posts, by skipping the first "offset" number of posts
     * @param limit the number of reported posts to be returned
     * @param offset the number of reported posts to be skipped
     * @return the list of reported post ids, ordered in descending order by the total number of reports
     */
    @GetMapping("/posts")
    public @ResponseBody List<Integer> getReportedPosts(@RequestParam("limit") int limit, @RequestParam("offset") int offset) {
        System.out.println("Admin Controller getting " + limit + " reported posts with offset " + offset);
        return adminService.getPosts(limit, offset);
    }

    /**
     * Returns the total number of users/posts in the system
     * @param model the given model, can be "users" or "posts"
     * @return the total number of users/posts in the system
     */
    @GetMapping("/total")
    public @ResponseBody int getTotalNumberOfModel(@RequestParam("model") String model) {
        try {
            return adminService.getTotalNumberOfModel(model);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
