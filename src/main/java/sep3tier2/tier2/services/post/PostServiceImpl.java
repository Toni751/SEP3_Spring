package sep3tier2.tier2.services.post;

import org.springframework.stereotype.Service;
import sep3tier2.tier2.models.Post;
import sep3tier2.tier2.models.PostAction;
import sep3tier2.tier2.models.PostShortVersion;

import java.util.List;

@Service
public class PostServiceImpl implements PostService
{
    @Override
    public void addPost(PostShortVersion post) throws Exception {

    }

    @Override
    public Post getPostById(int postId) throws Exception {
        return null;
    }

    @Override
    public void editPost(Post post) throws Exception {

    }

    @Override
    public void deletePost(int postId) throws Exception {

    }

    @Override
    public List<Post> getLatestPostsForUser(int id, int offset) {
        return null;
    }

    @Override
    public List<Post> getLatestPostsByUser(int id, int offset) {
        return null;
    }

    @Override
    public void postPostAction(PostAction postAction) throws Exception {

    }
}
