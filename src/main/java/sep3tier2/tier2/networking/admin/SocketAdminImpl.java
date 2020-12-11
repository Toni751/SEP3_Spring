package sep3tier2.tier2.networking.admin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sep3tier2.tier2.models.*;
import sep3tier2.tier2.networking.ServerConnectorImpl;
import sep3tier2.tier2.services.SocketsUtilMethods;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * The sockets class for handling administrator requests
 * @version 1.0
 * @author Group1
 */
@Component
public class SocketAdminImpl implements SocketAdmin
{
    private Gson gson;

    @Autowired
    ServerConnectorImpl serverConnectorImpl;

    @Autowired
    SocketsUtilMethods socketsUtilMethods;

    /**
     * No-argument constructor which initialized the gson object
     */
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
    public List<Integer> getPosts(int limit, int offset) {
        List<Integer> paginationInts = new ArrayList<>();
        paginationInts.add(limit);
        paginationInts.add(offset);
        Request request = new Request(ActionType.ADMIN_GET_POSTS, paginationInts);
        return socketsUtilMethods.getIntegerList(request);
    }

    @Override
    public int getTotalNumberOfModel(String model) {
        Request request = new Request(ActionType.ADMIN_GET_NUMBER, model);
        return socketsUtilMethods.requestWithIntegerReturnTypeWithoutImages(request);
    }
}
