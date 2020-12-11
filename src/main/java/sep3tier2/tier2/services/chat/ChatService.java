package sep3tier2.tier2.services.chat;

import sep3tier2.tier2.models.Message;
import sep3tier2.tier2.models.UserShortVersionWithMessage;

import java.util.List;

/**
 * The interface for the service class which manages chat actions
 * @version 1.0
 * @author Group1
 */
public interface ChatService
{
    /**
     * Creates a new message and returns the id of the created message and the id of the associated created notification
     * @param message the message to be created
     * @return the id of the created message and the id of the created notification
     */
    List<Integer> addMessage(Message message) throws Exception;

    /**
     * Deletes a message with a given id
     * @param messageId the id of the message to be deleted
     */
    void deleteMessage(int messageId) throws Exception;

    /**
     * Returns a list of most recent messages(with owner) belonging to a given user, skipping the first "offset" messages
     * @param userId the id of the user
     * @param offset the number of messages to be skipped
     * @return the list of the user's last messages, with owners
     */
    List<UserShortVersionWithMessage> getLastMessagesForUser(int userId, int offset) throws Exception;

    /**
     * Returns a list of recent messages between 2 users, skipping the first "offset" messages
     * @param firstUserId the first user id
     * @param secondUserId the second user id
     * @param offset the number of messages to be skipped
     * @return a list of recent messages between the 2 users, if any
     */
    List<Message> getConversationForUsers(int firstUserId, int secondUserId, int offset) throws Exception;
}
