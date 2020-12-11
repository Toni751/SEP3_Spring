package sep3tier2.tier2.networking.chat;

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

/**
 * The sockets class for handling chat requests
 * @version 1.0
 * @author Group1
 */
@Component
public class SocketChatImpl implements SocketChat
{
    private Gson gson;

    @Autowired
    ServerConnector serverConnector;

    @Autowired
    SocketsUtilMethods socketsUtilMethods;

    /**
     * No-argument constructor which initialized the gson object
     */
    public SocketChatImpl() {
        gson = new Gson();
    }

    @Override
    public List<Integer> addMessage(Message message) throws Exception {
        List<Integer> result = new ArrayList<>();
        if(!message.hasImage()) {
            message.clearPicture();
            result = socketsUtilMethods.getIntegerList(new Request(ActionType.MESSAGE_CREATE, message));
            if(result.contains(-1))
                throw new Exception("Could not add message");
        }
        else {
            List<byte[]> images = new ArrayList<>();
            images.add(message.getPicture());
            message.clearPicture();
            ActualRequest requestResponse = serverConnector.requestToServer(new ActualRequest(new Request(ActionType.MESSAGE_CREATE, message), images));
            if (requestResponse == null || requestResponse.getRequest() == null)
                throw new Exception("Could not add message");

            Type postListType = new TypeToken<List<Integer>>(){}.getType();
            result = gson.fromJson(requestResponse.getRequest().getArgument().toString(), postListType);
            if(result.contains(-1))
                throw new Exception("Could not add message");
        }
        return result;
    }

    @Override
    public void deleteMessage(int messageId) throws Exception {
        boolean bool = socketsUtilMethods.requestWithBooleanReturnTypeWithoutImages(new Request(ActionType.MESSAGE_DELETE, messageId));
        if(!bool)
            throw new Exception("Could not delete message with id" + messageId);
    }

    @Override
    public List<UserShortVersionWithMessage> getLastMessagesForUser(int userId, int offset) throws Exception {
        List<Integer> ints = new ArrayList<>();
        ints.add(userId);
        ints.add(offset);
        Request request = new Request(ActionType.MESSAGE_GET_LATEST, ints);
        ActualRequest response = serverConnector.requestToServer(new ActualRequest(request, null));
        if(response == null || response.getRequest() == null)
            throw new Exception("Could not get last messages for user " + userId);

        List<UserShortVersionWithMessage> users = new ArrayList<>();
        if(response.getRequest().getArgument() == null || response.getImages() == null)
            return users;

        Type userListType = new TypeToken<List<UserShortVersionWithMessage>>(){}.getType();
        users = gson.fromJson(response.getRequest().getArgument().toString(), userListType);
        for (int i = 0; i < users.size(); i++) {
            users.get(i).setAvatar(response.getImages().get(i));
        }
        return users;
    }

    @Override
    public List<Message> getConversationForUsers(int firstUserId, int secondUserId, int offset) throws Exception {
        List<Integer> ints = new ArrayList<>();
        ints.add(firstUserId);
        ints.add(secondUserId);
        ints.add(offset);
        Request request = new Request(ActionType.MESSAGE_GET_CONVERSATION, ints);
        ActualRequest response = serverConnector.requestToServer(new ActualRequest(request, null));

        if(response == null || response.getRequest() == null)
            throw new Exception("Could not get conversation for users " + firstUserId + ", " + secondUserId);

        List<Message> messages = new ArrayList<>();
        if(response.getRequest().getArgument().toString().equals("null"))
            return messages;

        Type messageListType = new TypeToken<List<Message>>(){}.getType();
        messages = gson.fromJson(response.getRequest().getArgument().toString(), messageListType);
        if(response.getImages() != null && response.getImages().size() > 0)
        {
            int imageCount = 0;
            for (Message message : messages) {
                if(message.hasImage())
                    message.setPicture(response.getImages().get(imageCount++));
            }
        }
        return messages;
    }
}
