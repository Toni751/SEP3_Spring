package sep3tier2.tier2.services.post;

import sep3tier2.tier2.models.post.Comment;
import sep3tier2.tier2.models.post.CommentForPost;
import sep3tier2.tier2.models.post.PostAction;
import sep3tier2.tier2.models.post.PostShortVersion;
import sep3tier2.tier2.models.user.UserShortVersion;

import java.util.List;

/**
 * The interface for the service class which manages posts actions
 * @version 1.0
 * @author Group1
 */
public interface PostService
{
    /**
     * Adds a given post to a given user
     * @param post the post to be added, with the owner id
     * @return the id of the created post
     */
    int addPost(PostShortVersion post) throws Exception;

    /**
     * Gets a given post by id, for a given user
     * @param postId the id of the post
     * @param userId the id of the user
     * @return the post with the given id, including the user's interactions with it(if the user liked/reported it)
     */
    PostShortVersion getPostById(int postId, int userId) throws Exception;

    /**
     * Edits a post at a given id
     * @param post the new value for the post
     */
    void editPost (PostShortVersion post) throws Exception;

    /**
     * Deletes a post at a given id
     * @param postId the id of the post
     */
    void deletePost(int postId) throws Exception;

    /**
     * Retrieves a list of relevant posts for a user(i.e. his own, his friend's, his following page's) in reverse chronological order
     * @param id the user id
     * @param offset the number of posts to be skipped
     * @return a list with relevant post ids for a user
     */
    List<Integer> getLatestPostsForUser(int id, int offset) throws Exception;

    /**
     * Gets a list of relevant posts created by a user, in reverse chronological order
     * @param id the user id
     * @param offset the number of posts to be skipped
     * @return a list with post ids created by the user
     */
    List<Integer> getLatestPostsByUser(int id, int offset) throws Exception;

    /**
     * Creates a new action for a post(react or report) by a user
     * @param postAction the post action to be added
     * @return Ok if the action was successful, Bad_Request otherwise
     */
    int postPostAction(PostAction postAction) throws Exception;

    /**
     * Creates a comment for a given post
     * @param comment the comment to be created
     * @return the id of the created comment
     */
    int addCommentToPost(CommentForPost comment) throws Exception;

    /**
     * Deletes a comment at a given id
     * @param commentId the id of the comment to be deleted
     */
    void deleteCommentFromPost(int commentId) throws Exception;

    /**
     * Retrieves all comments for a post, in reverse chronological order
     * @param postId the id of the post
     * @return the list with all the comments belonging to the post
     */
    List<Comment> getAllCommentsForPost(int postId) throws Exception;

    /**
     * Retrieves all users who reacted to a post
     * @param postId the id of the post
     * @return the list with all the users in alphabetical order
     */
    List<UserShortVersion> getAllLikesForPost(int postId) throws Exception;
}
