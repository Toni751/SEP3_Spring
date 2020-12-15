package sep3tier2.tier2.services.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sep3tier2.tier2.models.post.Comment;
import sep3tier2.tier2.models.post.CommentForPost;
import sep3tier2.tier2.models.post.PostAction;
import sep3tier2.tier2.models.post.PostShortVersion;
import sep3tier2.tier2.models.user.UserShortVersion;
import sep3tier2.tier2.networking.post.SocketPost;

import java.util.List;

/**
 * The service class for handling posts requests
 * @version 1.0
 * @author Group1
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    SocketPost socketPost;

    @Override
    public int addPost(PostShortVersion post) throws Exception {
        int postId = socketPost.addPost(post);
        if (postId < 0)
            throw new Exception("Error creating post, check post object fields");
        return postId;

    }

    @Override
    public PostShortVersion getPostById(int postId, int userId) throws Exception {
        if (postId < 0 || userId < 0)
            throw new Exception("Invalid request parameters");
        try {
            return socketPost.getPostById(postId, userId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void editPost(PostShortVersion post) throws Exception {
        try {
            socketPost.editPost(post);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deletePost(int postId) throws Exception {
        if (postId < 0)
            throw new Exception("Invalid post id");
        try {
            socketPost.deletePost(postId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Integer> getLatestPostsForUser(int id, int offset) throws Exception {
        if (id < 0 || offset < 0)
            throw new Exception("Invalid request parameters");
        return socketPost.getLatestPostsForUser(id, offset);
    }

    @Override
    public List<Integer> getLatestPostsByUser(int id, int offset) throws Exception {
        if (id < 0 || offset < 0)
            throw new Exception("Invalid request parameters");
        return socketPost.getLatestPostsByUser(id, offset);
    }

    @Override
    public int postPostAction(PostAction postAction) throws Exception {
        try {
            return socketPost.postPostAction(postAction);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public int addCommentToPost(CommentForPost comment) throws Exception {
        try {
            int commentId = socketPost.addCommentToPost(comment);
            if (commentId < 0)
                throw new Exception("Error creating comment, check comment object fields");
            return commentId;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteCommentFromPost(int commentId) throws Exception {
        if (commentId < 0)
            throw new Exception("Invalid comment id");
        try {
            socketPost.deleteComment(commentId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Comment> getAllCommentsForPost(int postId) throws Exception {
        if(postId < 0)
            throw new Exception("Invalid post id");
        try{
            return socketPost.getAllCommentsForPost(postId);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<UserShortVersion> getAllLikesForPost(int postId) throws Exception {
        if(postId < 0)
            throw new Exception("Invalid post id");
        try{
            return socketPost.getAllLikesForPost(postId);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
