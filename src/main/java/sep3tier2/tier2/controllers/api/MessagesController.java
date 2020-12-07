package sep3tier2.tier2.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sep3tier2.tier2.models.Message;
import sep3tier2.tier2.models.UserShortVersionWithMessage;
import sep3tier2.tier2.services.chat.ChatService;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessagesController
{
    @Autowired
    ChatService chatService;

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
