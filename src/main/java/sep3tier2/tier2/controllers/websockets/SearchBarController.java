package sep3tier2.tier2.controllers.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import sep3tier2.tier2.models.FilterUsersRequest;
import sep3tier2.tier2.models.Notification;
import sep3tier2.tier2.models.SearchBarUser;
import sep3tier2.tier2.models.UserAction;
import sep3tier2.tier2.services.user.UserService;

import java.util.List;

@Controller
public class SearchBarController
{
    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/filter")
    public void sendNotification(@Payload FilterUsersRequest request)
    {
        System.out.println("Filter string is " + request.getQueryString());
        try {
            List<SearchBarUser> searchBarUsers = userService.getUsersByFilter(request.getQueryString());
            messagingTemplate.convertAndSend("/topic/filter_result/" + request.getSenderId(), searchBarUsers);
        } catch (Exception e) {
            System.out.println("Sending error message to clients");
            messagingTemplate.convertAndSend("/topic/errors/" + request.getSenderId(), e.getMessage());
        }
    }
}
