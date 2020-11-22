package sep3tier2.tier2.networking.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sep3tier2.tier2.models.*;
import sep3tier2.tier2.networking.ServerConnectorImpl;

import java.util.ArrayList;
import java.util.List;

@Component
public class SocketAdminImpl implements SocketAdmin
{
    @Autowired
    ServerConnectorImpl serverConnectorImpl;

    @Override
    public List<UserShortVersion> getUsers(int limit, int offset) {
        List<Integer> paginationInts = new ArrayList<>();
        paginationInts.add(limit);
        paginationInts.add(offset);
        Request request = new Request(ActionType.ADMIN_GET_USERS, paginationInts);

        ActualRequest response = serverConnectorImpl.requestToServer(new ActualRequest(request, null));
        List<UserShortVersion> users = (List<UserShortVersion>) response.getRequest().getArgument();
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
        List<PostShortVersion> posts = (List<PostShortVersion>) response.getRequest().getArgument();
        for (int i = 0; i < response.getImages().size(); i++) {
            posts.get(i).setPicture(response.getImages().get(i));
        }
        return posts;
    }
}
