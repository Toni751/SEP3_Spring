package sep3tier2.tier2.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.server.ResponseStatusException;
import sep3tier2.tier2.models.Post;
import sep3tier2.tier2.models.PostAction;
import sep3tier2.tier2.models.PostShortVersion;
import sep3tier2.tier2.models.UserAction;
import sep3tier2.tier2.services.post.PostService;

import java.util.List;

@SessionScope
@RestController
@RequestMapping("/posts")
public class PostsController
{
    @Autowired
    PostService postService;

    @CrossOrigin(origins = "*")
    @PostMapping
    public HttpStatus addPost(@RequestBody PostShortVersion post)
    {
        System.out.println("Controller adding post");
        try{
            postService.addPost(post);
            return HttpStatus.CREATED;
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public @ResponseBody Post getPostById(@PathVariable int id)
    {
        System.out.println("Controller getting post by id " + id);
        try{
           return postService.getPostById(id);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public HttpStatus editPost(@PathVariable int id, @RequestBody Post post)
    {
        System.out.println("Controller editing post with id " + id);
        try{
            postService.editPost(post);
            return HttpStatus.OK;
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public HttpStatus deletePost(@PathVariable int id)
    {
        System.out.println("Controller deleting post by id " + id);
        try{
            postService.deletePost(id);
            return HttpStatus.OK;
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public @ResponseBody List<Post> getPostsForUser(@RequestParam("forId") int forId, @RequestParam("offset") int offset)
    {
        System.out.println("Controller getting news feed posts for user " + forId);
        try{
            return postService.getLatestPostsForUser(forId, offset);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

//    @CrossOrigin(origins = "*")
//    @GetMapping
//    public @ResponseBody List<Post> getPostsByUser(@RequestParam("byId") int byId, @RequestParam("offset") int offset)
//    {
//        System.out.println("Controller getting posts created by user " + byId);
//        try{
//            return postService.getLatestPostsByUser(byId, offset);
//        }
//        catch (Exception e){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }

    @CrossOrigin(origins = "*")
    @PostMapping("/actions")
    public HttpStatus postPostAction(@RequestBody PostAction postAction)
    {
        System.out.println("Controller post action " + postAction.getActionType() + " with value " + postAction.getValue());
        try{
            postService.postPostAction(postAction);
            return HttpStatus.OK;
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
