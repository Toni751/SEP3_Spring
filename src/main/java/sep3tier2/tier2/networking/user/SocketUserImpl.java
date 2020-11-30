package sep3tier2.tier2.networking.user;

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
public class SocketUserImpl implements SocketUser {
    private Gson gson;

    @Autowired
    ServerConnector serverConnector;

    @Autowired
    SocketsUtilMethods socketsUtilMethods;

    public SocketUserImpl() {
        gson = new Gson();
    }

    @Override
    public boolean addUser(User user) {
        Request request = new Request(ActionType.USER_REGISTER, user);
        return socketsUtilMethods.requestWithBooleanReturnTypeWithoutImages(request);
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
        System.out.println("Received logged in user");
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
        ActualRequest requestResponse = serverConnector.requestToServer(new ActualRequest(request, userImages));
        if (requestResponse == null || requestResponse.getRequest() == null)
            throw new Exception("User could not be edited");

        Boolean bool = gson.fromJson(requestResponse.getRequest().getArgument().toString(), Boolean.class);
        System.out.println("Boolean request result is " + bool);
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
        boolean bool = socketsUtilMethods.requestWithBooleanReturnTypeWithoutImages(request);
        if (!bool)
            throw new Exception("User could not be deleted");
    }

    @Override
    public int postUserAction(UserAction userAction) throws Exception
    {
        Request request = new Request(userAction.getActionType(), userAction);
        int createdNotificationId = socketsUtilMethods.requestWithIntegerReturnTypeWithoutImages(request);
        if (createdNotificationId <= -1)
            throw new Exception("User action could not be performed");
        //if there was no notification created for the given user action, it returns 0, and it returns -1 if an error occurred
        return createdNotificationId;
    }

    @Override
    public void deleteNotification(int notificationId) throws Exception {
        Request request = new Request(ActionType.USER_DELETE_NOTIFICATION, notificationId);
         boolean bool = socketsUtilMethods.requestWithBooleanReturnTypeWithoutImages(request);
        if (!bool)
            throw new Exception("Notification could not be deleted");
    }

    @Override
    public List<SearchBarUser> getUsersByFilter(String filter) {
        Request request = new Request(ActionType.USER_FILTER, filter);
        ActualRequest response = serverConnector.requestToServer(new ActualRequest(request, null));
        if(response == null || response.getRequest() == null || response.getRequest().getArgument() == null)
            return null;

        Type searchbarUserListType = new TypeToken<List<SearchBarUser>>(){}.getType();
        return gson.fromJson(response.getRequest().getArgument().toString(), searchbarUserListType);
    }
}
