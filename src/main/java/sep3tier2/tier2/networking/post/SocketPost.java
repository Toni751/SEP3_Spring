package sep3tier2.tier2.networking.post;

import sep3tier2.tier2.models.*;

import java.util.List;

public interface SocketPost
{
    int addPost(PostShortVersion post);
    Post getPostById(int postId) throws Exception;
    void editPost (PostShortVersion post) throws Exception;
    void deletePost(int postId) throws Exception;
    List<PostShortVersion> getLatestPostsForUser(int id, int offset);
    List<Integer> getLatestPostsByUser(int id, int offset);
    int postPostAction(PostAction postAction) throws Exception;
    int addCommentToPost(CommentForPost comment) throws Exception;
    void deleteComment(int commentId) throws Exception;
    List<Comment> getAllCommentsForPost(int postId) throws Exception;
    List<UserShortVersion> getAllLikesForPost(int postId) throws Exception;
}
