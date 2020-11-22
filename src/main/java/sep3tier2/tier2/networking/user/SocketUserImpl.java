package sep3tier2.tier2.networking.user;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sep3tier2.tier2.models.*;
import sep3tier2.tier2.networking.ServerConnector;
import sep3tier2.tier2.networking.ServerConnectorImpl;

import java.util.ArrayList;
import java.util.List;

@Component
public class SocketUserImpl implements SocketUser {
    private Gson gson;
    private JsonReader reader;

    @Autowired
    ServerConnector serverConnector;

    public SocketUserImpl() {
        gson = new Gson();
    }

    @Override
    public boolean addUser(User user) {
        Request request = new Request(ActionType.USER_REGISTER, user);
        ActualRequest registerResult = serverConnector.requestToServer(new ActualRequest(request, null));
        if (registerResult == null)
            return false;

        Boolean bool = gson.fromJson(registerResult.getRequest().getArgument().toString(), Boolean.class);
        System.out.println("Boolean result is " + bool);
        return bool;
    }

    @Override
    public UserShortVersion login(String email, String password) {
        LoginCredentials loginCredentials = new LoginCredentials(email, password);
        Request request = new Request(ActionType.USER_LOGIN, loginCredentials);
        ActualRequest response = serverConnector.requestToServer(new ActualRequest(request, null));
        if (response == null)
            return null;
        // return gson.fromJson(response.getArgument().toString(), UserShortVersion.class);
        UserShortVersion responseArgument = gson.fromJson(response.getRequest().getArgument().toString(), UserShortVersion.class);
        if (response.getImages() != null) {
            responseArgument.setAvatar(response.getImages().get(0));
        }
        return responseArgument;
    }

    @Override
    public void editUser(User user) throws Exception{
        List<byte[]> userImages = new ArrayList<>();
        if (user.getAvatar() != null) {
            userImages.add(user.getAvatar());
            user.clearAvatar();
        }
        if (user.getProfileBackground() != null) {
            userImages.add(user.getProfileBackground());
            user.clearProfileBackground();
        }
        Request request = new Request(ActionType.USER_EDIT, user);
        ActualRequest actualRequest = new ActualRequest(request, userImages);
        ActualRequest response = serverConnector.requestToServer(actualRequest);

        Boolean bool = gson.fromJson(response.getRequest().getArgument().toString(), Boolean.class);
        System.out.println("Boolean edit result is " + bool);
        if (!bool)
            throw new Exception("User could not be edited");
    }

    @Override
    public User getUserById(int senderId, int receiverId) {
        List<Integer> userIds = new ArrayList<>();
        userIds.add(senderId);
        userIds.add(receiverId);
        Request request = new Request(ActionType.USER_GET_BY_ID, userIds);
        ActualRequest userResponse = serverConnector.requestToServer(new ActualRequest(request, null));

        User user = gson.fromJson(userResponse.getRequest().getArgument().toString(), User.class);;
        if (userResponse.getImages() != null)
        {
            user.setAvatar(userResponse.getImages().get(0));
            if (userResponse.getImages().size() == 2)
                user.setProfileBackground(userResponse.getImages().get(1));
        }
        return user;
    }

    @Override
    public void deleteUser(int id) throws Exception {
        Request request = new Request(ActionType.USER_DELETE, id);
        ActualRequest deleteUserResponse = serverConnector.requestToServer(new ActualRequest(request, null));

        Boolean bool = gson.fromJson(deleteUserResponse.getRequest().getArgument().toString(), Boolean.class);
        System.out.println("Boolean delete result is " + bool);
        if (!bool)
            throw new Exception("User could not be deleted");

    }

    @Override
    public void postUserAction(UserAction userAction) throws Exception
    {
        Request request = new Request(userAction.getActionType(), userAction);
        ActualRequest userActionResponse = serverConnector.requestToServer(new ActualRequest(request, null));

        Boolean bool = gson.fromJson(userActionResponse.getRequest().getArgument().toString(), Boolean.class);
        System.out.println("Boolean user action result is " + bool);
        if (!bool)
            throw new Exception("User action could not be performed");
    }
}
