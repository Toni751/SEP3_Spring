package sep3tier2.tier2.services.post;

import sep3tier2.tier2.models.Post;
import sep3tier2.tier2.models.PostAction;
import sep3tier2.tier2.models.PostShortVersion;
import sep3tier2.tier2.models.UserAction;

import java.util.List;

public interface PostService
{
    void addPost(PostShortVersion post) throws Exception;
    Post getPostById(int postId) throws Exception;
    void editPost (Post post) throws Exception;
    void deletePost(int postId) throws Exception;
    List<Post> getLatestPostsForUser(int id, int offset); //news feed for the given user
    List<Post> getLatestPostsByUser(int id, int offset); //posts created by the given user, for when seeing profile
    void postPostAction(PostAction postAction) throws Exception;
}
