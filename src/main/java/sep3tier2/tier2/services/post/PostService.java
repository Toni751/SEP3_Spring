package sep3tier2.tier2.services.post;

import sep3tier2.tier2.models.*;

import java.util.List;

public interface PostService
{
    int addPost(PostShortVersion post) throws Exception;
    Post getPostById(int postId) throws Exception;
    void editPost (PostShortVersion post) throws Exception;
    void deletePost(int postId) throws Exception;
    List<PostShortVersion> getLatestPostsForUser(int id, int offset) throws Exception; //news feed for the given user
    List<PostShortVersion> getLatestPostsByUser(int id, int offset) throws Exception; //posts created by the given user, for when seeing profile
    int postPostAction(PostAction postAction) throws Exception;
    int addCommentToPost(CommentForPost comment) throws Exception;
    void deleteCommentFromPost(int commentId) throws Exception;
    List<Comment> getAllCommentsForPost(int postId) throws Exception;
    List<UserShortVersion> getAllLikesForPost(int postId) throws Exception;
}
