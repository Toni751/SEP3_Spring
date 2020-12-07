package sep3tier2.tier2.networking.post;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sep3tier2.tier2.models.*;
import sep3tier2.tier2.networking.ServerConnector;
import sep3tier2.tier2.services.SocketsUtilMethods;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class SocketPostImpl implements SocketPost
{
    private Gson gson;

    @Autowired
    ServerConnector serverConnector;

    @Autowired
    SocketsUtilMethods socketsUtilMethods;

    public SocketPostImpl() {
        gson = new Gson();
    }

    @Override
    public int addPost(PostShortVersion post){
        List<byte[]> images = new ArrayList<>();
        if (post.getPicture() != null){
            images.add(post.getPicture());
            post.clearPicture();
        }
        Request request = new Request(ActionType.POST_CREATE, post);
        ActualRequest response = serverConnector.requestToServer(new ActualRequest(request, images));
        if (response == null)
            return -1;

        Integer postId = gson.fromJson(response.getRequest().getArgument().toString(), Integer.class);
        System.out.println("Created post id is " + postId);
        return postId;
    }

    @Override
    public PostShortVersion getPostById(int postId, int userId) throws Exception {
        List<Integer> ints = new ArrayList<>();
        ints.add(postId);
        ints.add(userId);
        Request request = new Request(ActionType.POST_GET_BY_ID, ints);
        ActualRequest response = serverConnector.requestToServer(new ActualRequest(request, null));
        if (response.getRequest() == null || response.getRequest().getArgument() == null)
            throw new Exception("Could not retrieve post with id " + postId);
        PostShortVersion post = gson.fromJson(response.getRequest().getArgument().toString(), PostShortVersion.class);
        if(response.getImages() != null)
        {
            post.getOwner().setAvatar(response.getImages().get(0));
//            response.getImages().remove(0);

            if(post.hasImage())
                post.setPicture(response.getImages().get(1));
//            {
//                response.getImages().remove(0);
//            }
//            if(response.getImages() != null && !response.getImages().isEmpty()) {
//                for (int i = 0; i < response.getImages().size(); i++) {
//                    post.getComments().get(i).getOwner().setAvatar(response.getImages().get(i));
//                }
//            }
        }

        return post;
    }

    @Override
    public void editPost(PostShortVersion post) throws Exception {
        List<byte[]> postImage = new ArrayList<>();
        if (post.getPicture() != null){
            postImage.add(post.getPicture());
            post.clearPicture();
        }
        Request request = new Request(ActionType.POST_EDIT, post);
        ActualRequest response = serverConnector.requestToServer(new ActualRequest(request, postImage));

        Boolean bool = gson.fromJson(response.getRequest().getArgument().toString(), Boolean.class);
        System.out.println("Boolean edit result is " + bool);
        if (!bool)
            throw new Exception("Post could not be edited");
    }

    @Override
    public void deletePost(int postId) throws Exception {
        Request request = new Request(ActionType.POST_DELETE, postId);
        boolean bool = socketsUtilMethods.requestWithBooleanReturnTypeWithoutImages(request);
        if (!bool)
            throw new Exception("Post could not be deleted");
    }

    @Override
    public List<Integer> getLatestPostsForUser(int id, int offset) {
        List<Integer> integers = new ArrayList<>();
        integers.add(id);
        integers.add(offset);
        Request request = new Request(ActionType.POST_GET_FOR_USER, integers);
        return socketsUtilMethods.getIntegerList(request);
    }

    @Override
    public List<Integer> getLatestPostsByUser(int id, int offset) {
        List<Integer> integers = new ArrayList<>();
        integers.add(id);
        integers.add(offset);
        Request request = new Request(ActionType.POST_GET_BY_USER, integers);
        return socketsUtilMethods.getIntegerList(request);
    }

    @Override
    public int postPostAction(PostAction postAction) throws Exception {
        Request request = new Request(postAction.getActionType(), postAction);
        int createdNotificationId = socketsUtilMethods.requestWithIntegerReturnTypeWithoutImages(request);
        if (createdNotificationId <= -1)
            throw new Exception("Post action could not be performed");
        //if there was no notification created for the given post action, it returns 0, and it returns -1 if an error occurred
        return createdNotificationId;
    }

    @Override
    public int addCommentToPost(CommentForPost comment) throws Exception {
        Request request = new Request(ActionType.POST_ADD_COMMENT, comment);
        int createdCommentId = socketsUtilMethods.requestWithIntegerReturnTypeWithoutImages(request);
        if (createdCommentId <= 0)
            throw new Exception("Comment could not be added");
        return createdCommentId;
    }

    @Override
    public void deleteComment(int commentId) throws Exception {
        Request request = new Request(ActionType.POST_DELETE_COMMENT, commentId);
        boolean bool = socketsUtilMethods.requestWithBooleanReturnTypeWithoutImages(request);
        if (!bool)
            throw new Exception("Comment could not be deleted");
    }

    @Override
    public List<Comment> getAllCommentsForPost(int postId) throws Exception {
        Request request = new Request(ActionType.POST_GET_COMMENTS, postId);
        ActualRequest response = serverConnector.requestToServer(new ActualRequest(request, null));
        if(response == null || response.getRequest() == null)
            throw new Exception("Could not retrieve comments for post " + postId);
        List<Comment> comments = new ArrayList<>();
        if(response.getRequest().getArgument() == null || response.getImages() == null)
            return comments;

        Type commentListType = new TypeToken<List<Comment>>(){}.getType();
        comments = gson.fromJson(response.getRequest().getArgument().toString(), commentListType);
        for (int i = 0; i < comments.size(); i++) {
            comments.get(i).getOwner().setAvatar(response.getImages().get(i));
        }
        return comments;
    }

    @Override
    public List<UserShortVersion> getAllLikesForPost(int postId) throws Exception {
        Request request = new Request(ActionType.POST_GET_LIKES, postId);
        List<UserShortVersion> response = socketsUtilMethods.requestUsersWithImages(request);
        if (response == null)
            throw new Exception("Could not retrieve likes for post " + postId);

        return response;
    }
}
