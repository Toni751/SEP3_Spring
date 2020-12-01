package sep3tier2.tier2.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.server.ResponseStatusException;
import sep3tier2.tier2.models.*;
import sep3tier2.tier2.services.post.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController
{
    @Autowired
    PostService postService;

    @CrossOrigin(origins = "*")
    @PostMapping
    public @ResponseBody int addPost(@RequestBody PostShortVersion post) {
        System.out.println("Controller adding post");
        try {
            return postService.addPost(post);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping()
    public @ResponseBody PostShortVersion getPostById(@RequestParam("postId") int postId, @RequestParam("userId") int userId) {
        System.out.println("Controller getting post by id " + postId + " by user " + userId);
        try {
            return postService.getPostById(postId, userId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public HttpStatus editPost(@PathVariable int id, @RequestBody PostShortVersion post) {
        System.out.println("Controller editing post with id " + id);
        try {
            postService.editPost(post);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public HttpStatus deletePost(@PathVariable int id) {
        System.out.println("Controller deleting post by id " + id);
        try {
            postService.deletePost(id);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/wall")
    public @ResponseBody List<Integer> getPostsForUser(@RequestParam("forId") int forId, @RequestParam("offset") int offset) {
        System.out.println("Controller getting news feed posts for user " + forId);
        try {
            return postService.getLatestPostsForUser(forId, offset);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/profile")
    public @ResponseBody List<Integer> getPostsByUser(@RequestParam("byId") int byId, @RequestParam("offset") int offset) {
        System.out.println("Controller getting posts created by user " + byId);
        try {
            return postService.getLatestPostsByUser(byId, offset);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/actions")
    public HttpStatus postPostAction(@RequestBody PostAction postAction) {
        System.out.println("Controller post action " + postAction.getActionType() + " with value " + postAction.getValue());
        try {
            postService.postPostAction(postAction);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/{id}")
    public int addCommentToPost(@PathVariable int id, @RequestBody Comment comment) {
        System.out.println("Controller post adding comment to post " + id);
        try {
            return postService.addCommentToPost(new CommentForPost(comment.getOwner().getUserId(), id, comment.getContent(), comment.getTimeStamp()));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/comments/{id}")
    public HttpStatus deleteComment(@PathVariable int id) {
        System.out.println("Controller post deleting comment " + id);
        try {
            postService.deleteCommentFromPost(id);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}/comments")
    public List<Comment> getAllCommentsForPost(@PathVariable int id)
    {
        System.out.println("Controller post getting all comments for post " + id);
        try{
            return postService.getAllCommentsForPost(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}/likes")
    public List<UserShortVersion> getAllLikesForPost(@PathVariable int id)
    {
        System.out.println("Controller post getting all likes for post " + id);
        try{
            return postService.getAllLikesForPost(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
