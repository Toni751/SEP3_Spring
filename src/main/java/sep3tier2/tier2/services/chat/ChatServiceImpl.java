package sep3tier2.tier2.services.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sep3tier2.tier2.models.chat.Message;
import sep3tier2.tier2.models.user.UserShortVersionWithMessage;
import sep3tier2.tier2.networking.chat.SocketChat;

import java.util.List;

/**
 * The service class for handling chat requests
 * @version 1.0
 * @author Group1
 */
@Service
public class ChatServiceImpl implements ChatService
{
    @Autowired
    SocketChat socketChat;

    @Override
    public List<Integer> addMessage(Message message) throws Exception {
        try {
            return socketChat.addMessage(message);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteMessage(int messageId) throws Exception {
        if(messageId <= 0)
            throw new Exception("Invalid message id " + messageId);
        try {
            socketChat.deleteMessage(messageId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<UserShortVersionWithMessage> getLastMessagesForUser(int userId, int offset) throws Exception {
        if(userId <= 0 || offset < 0)
            throw new Exception("Invalid request parameters");
        try {
            return socketChat.getLastMessagesForUser(userId, offset);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Message> getConversationForUsers(int firstUserId, int secondUserId, int offset) throws Exception {
        if(firstUserId <= 0 || secondUserId <= 0 || offset < 0)
            throw new Exception("Invalid request parameters");
        try {
            return socketChat.getConversationForUsers(firstUserId, secondUserId, offset);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
