package sep3tier2.tier2.controllers.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import sep3tier2.tier2.models.user.FilterUsersRequest;
import sep3tier2.tier2.models.user.SearchBarUser;
import sep3tier2.tier2.services.user.UserService;
import java.util.List;

/**
 * WebSockets Controller for search bar-related requests
 * @version 1.0
 * @author Group1
 */
@Controller
public class SearchBarController
{
    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * Filters the users by a given query string and sends the result to the sender
     * @param request the request containing the sender id and the query string
     */
    @MessageMapping("/filter")
    public void sendNotification(@Payload FilterUsersRequest request)
    {
        try {
            List<SearchBarUser> searchBarUsers = userService.getUsersByFilter(request.getQueryString());
            messagingTemplate.convertAndSend("/topic/filter_result/" + request.getSenderId(), searchBarUsers);
        } catch (Exception e) {
            messagingTemplate.convertAndSend("/topic/errors/" + request.getSenderId(), e.getMessage());
        }
    }
}
