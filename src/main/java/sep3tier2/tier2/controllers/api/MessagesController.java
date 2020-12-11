package sep3tier2.tier2.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sep3tier2.tier2.models.Message;
import sep3tier2.tier2.models.UserShortVersionWithMessage;
import sep3tier2.tier2.services.chat.ChatService;

import java.util.List;

/**
 * Rest API Controller for messages-related requests at the endpoint "/messages"
 * @version 1.0
 * @author Group1
 */
@RestController
@RequestMapping("/messages")
public class MessagesController
{
    @Autowired
    ChatService chatService;

    /**
     * Returns a list of most recent messages(with owner) belonging to a given user, skipping the first "offset" messages
     * @param userId the id of the user
     * @param offset the number of messages to be skipped
     * @return the list of the user's last messages, with owners
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/recent")
    public @ResponseBody List<UserShortVersionWithMessage> getLastMessagesForUser(
            @RequestParam("userId") int userId, @RequestParam("offset") int offset)
    {
        System.out.println("Controller getting last messages for user " + userId + " with offset " + offset);
        try {
            return chatService.getLastMessagesForUser(userId, offset);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Returns a list of recent messages between 2 users, skipping the first "offset" messages
     * @param firstUserId the first user id
     * @param secondUserId the second user id
     * @param offset the number of messages to be skipped
     * @return a list of recent messages between the 2 users, if any
     */
    @CrossOrigin(origins = "*")
    @GetMapping()
    public @ResponseBody List<Message> getConversationForUsers(@RequestParam("firstUserId") int firstUserId,
            @RequestParam("secondUserId") int secondUserId, @RequestParam("offset") int offset)
    {
        System.out.println("Controller getting conversation for users " + firstUserId + ", " + secondUserId + " with offset " + offset);
        try {
            return chatService.getConversationForUsers(firstUserId, secondUserId, offset);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
