package sep3tier2.tier2.networking.admin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sep3tier2.tier2.models.*;
import sep3tier2.tier2.networking.ServerConnectorImpl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class SocketAdminImpl implements SocketAdmin
{
    Gson gson;
    @Autowired
    ServerConnectorImpl serverConnectorImpl;

    public SocketAdminImpl() {
        gson = new Gson();
    }

    @Override
    public List<UserShortVersion> getUsers(int limit, int offset) {
        List<Integer> paginationInts = new ArrayList<>();
        paginationInts.add(limit);
        paginationInts.add(offset);
        Request request = new Request(ActionType.ADMIN_GET_USERS, paginationInts);

        ActualRequest response = serverConnectorImpl.requestToServer(new ActualRequest(request, null));
        if (response.getRequest().getArgument() == null || response.getImages() == null)
            return null;
        Type userListType = new TypeToken<List<UserShortVersion>>(){}.getType();
        System.out.println("User list for admin is " + response.getRequest().getArgument().toString());
        List<UserShortVersion> users = gson.fromJson(response.getRequest().getArgument().toString(), userListType);
        for (int i = 0; i < response.getImages().size(); i++) {
            users.get(i).setAvatar(response.getImages().get(i));
        }
        return users;
    }

    @Override
    public List<PostShortVersion> getPosts(int limit, int offset) {
        List<Integer> paginationInts = new ArrayList<>();
        paginationInts.add(limit);
        paginationInts.add(offset);
        Request request = new Request(ActionType.ADMIN_GET_POSTS, paginationInts);

        ActualRequest response = serverConnectorImpl.requestToServer(new ActualRequest(request, null));
        if (response.getRequest().getArgument() == null || response.getImages() == null)
            return null;
        Type postListType = new TypeToken<List<PostShortVersion>>(){}.getType();
        System.out.println("Post list for admin is " + response.getRequest().getArgument().toString());
        List<PostShortVersion> posts = gson.fromJson(response.getRequest().getArgument().toString(), postListType);
        for (int i = 0; i < response.getImages().size(); i++) {
            posts.get(i).setPicture(response.getImages().get(i));
        }
        return posts;
    }

    @Override
    public int getTotalNumberOfModel(String model) {
        Request request = new Request(ActionType.ADMIN_GET_NUMBER, model);
        ActualRequest response = serverConnectorImpl.requestToServer(new ActualRequest(request, null));
        return gson.fromJson(response.getRequest().getArgument().toString(), Integer.class);
    }
}
