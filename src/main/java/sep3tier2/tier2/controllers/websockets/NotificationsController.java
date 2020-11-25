package sep3tier2.tier2.controllers.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
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

    @MessageMapping("/note")
   // @SendToUser("/topic/notifications")
    public void sendNotification(@Payload UserAction userAction)
    {
        System.out.println(userAction.toString());
        //messagingTemplate.convertAndSendToUser(String.valueOf(userAction.getSenderId()),"/topic/notifications", userAction);
        messagingTemplate.convertAndSend("/topic/notifications/" + userAction.getReceiverId(), userAction);
        try {
            userService.postUserAction(userAction);
        } catch (Exception e) {
            System.out.println("Sending error message to clients");
           messagingTemplate.convertAndSend("/topic/errors/" + userAction.getSenderId(), userAction.getActionType() + "_ERROR");
           messagingTemplate.convertAndSend("/topic/errors/" + userAction.getReceiverId(), userAction.getActionType() + "_ERROR");
        }
      // return userAction;
    }
}
