package sep3tier2.tier2.networking.post;

import sep3tier2.tier2.models.Post;
import sep3tier2.tier2.models.PostAction;
import sep3tier2.tier2.models.PostShortVersion;

import java.util.List;

public interface SocketPost
{
    void addPost(PostShortVersion post) throws Exception;
    Post getPostById(int postId) throws Exception;
    void editPost (Post post) throws Exception;
    void deletePost(int postId) throws Exception;
    List<Post> getLatestPostsForUser(int id, int offset);
    List<Post> getLatestPostsByUser(int id, int offset);
    void postPostAction(PostAction postAction) throws Exception;
}
