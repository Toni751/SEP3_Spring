package sep3tier2.tier2.controllers.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import sep3tier2.tier2.models.UserAction;
import sep3tier2.tier2.services.user.UserService;

@Controller
public class NotificationsController
{
    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/notifications")
    public void sendNotification(@Payload UserAction userAction)
    {
        System.out.println(userAction.toString());
        messagingTemplate.convertAndSendToUser(Integer.toString(userAction.getReceiverId()),"/queue/notifications", userAction);
        //messagingTemplate.convertAndSend("/queue/notifications/" + userAction.getReceiverId(), userAction);
        try {
            userService.postUserAction(userAction);
        } catch (Exception e) {
           messagingTemplate.convertAndSend("/queue/errors/" + userAction.getSenderId(), "Could not post user action " + userAction.getActionType());
           messagingTemplate.convertAndSend("/queue/errors/" + userAction.getReceiverId(), "Could not post user action " + userAction.getActionType());
        }
    }
}
