package sep3tier2.tier2.networking.chat;

import sep3tier2.tier2.models.Message;
import sep3tier2.tier2.models.UserShortVersionWithMessage;

import java.util.List;

public interface SocketChat
{
    List<Integer> addMessage(Message message) throws Exception;
    void deleteMessage(int messageId) throws Exception;
    List<UserShortVersionWithMessage> getLastMessagesForUser(int userId, int offset) throws Exception;
    List<Message> getConversationForUsers(int firstUserId, int secondUserId, int offset) throws Exception;
}
