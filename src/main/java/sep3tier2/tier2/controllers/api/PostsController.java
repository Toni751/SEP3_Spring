package sep3tier2.tier2.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.server.ResponseStatusException;
import sep3tier2.tier2.models.*;
import sep3tier2.tier2.services.post.PostService;

import java.util.List;

/**
 * Rest API Controller for posts-related requests at the endpoint "/posts"
 * @version 1.0
 * @author Group1
 */
@RestController
@RequestMapping("/posts")
public class PostsController
{
    @Autowired
    PostService postService;

    /**
     * Adds a given post to a given user
     * @param post the post to be added, with the owner id
     * @return the id of the created post
     */
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

    /**
     * Gets a given post by id, for a given user
     * @param postId the id of the post
     * @param userId the id of the user
     * @return the post with the given id, including the user's interactions with it(if the user liked/reported it)
     */
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

    /**
     * Edits a post at a given id
     * @param id the id of the post to be edited
     * @param post the new value for the post
     * @return Ok if the action was successful, Bad_Request otherwise
     */
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

    /**
     * Deletes a post at a given id
     * @param id the id of the post to be deleted
     * @return Ok if the action was successful, Bad_Request otherwise
     */
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

    /**
     * Retrieves a list of relevant posts for a user(i.e. his own, his friend's, his following page's) in reverse chronological order
     * @param forId the user id
     * @param offset the number of posts to be skipped
     * @return a list with relevant post ids for a user
     */
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

    /**
     * Gets a list of relevant posts created by a user, in reverse chronological order
     * @param byId the user id
     * @param offset the number of posts to be skipped
     * @return a list with post ids created by the user
     */
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

    /**
     * Creates a new action for a post(react or report) by a user
     * @param postAction the post action to be added
     * @return Ok if the action was successful, Bad_Request otherwise
     */
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

    /**
     * Creates a comment for a given post
     * @param id the id of the post
     * @param comment the comment to be created
     * @return the id of the created comment
     */
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

    /**
     * Deletes a comment at a given id
     * @param id the id of the comment to be deleted
     * @return Ok if the action was successful, Bad_Request otherwise
     */
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

    /**
     * Retrieves all comments for a post, in reverse chronological order
     * @param id the id of the post
     * @return the list with all the comments belonging to the post
     */
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

    /**
     * Retrieves all users who reacted to a post
     * @param id the id of the post
     * @return the list with all the users in alphabetical order
     */
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
