package sep3tier2.tier2.controllers.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import sep3tier2.tier2.models.Notification;
import sep3tier2.tier2.models.UserAction;
import sep3tier2.tier2.services.user.UserService;

import java.util.List;

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
        try {
            int notificationId = userService.postUserAction(userAction);
            Notification notification = new Notification(notificationId, userAction.getSenderId(), userAction.getSenderName(), userAction.getReceiverId(), userAction.getActionType());
            messagingTemplate.convertAndSend("/topic/notifications/" + userAction.getReceiverId(), notification);
        } catch (Exception e) {
            System.out.println("Sending error message to clients");
           messagingTemplate.convertAndSend("/topic/errors/" + userAction.getSenderId(), userAction.getActionType() + "_ERROR");
           messagingTemplate.convertAndSend("/topic/errors/" + userAction.getReceiverId(), userAction.getActionType() + "_ERROR");
        }
      // return userAction;
    }

    @MessageMapping("/logout")
    public void logoutUser(@Payload int userId)
    {
        System.out.println("Logging out user with id " + userId);
        try {
            List<Integer> onlineFriendIds = userService.userLogInOrOut(userId, true);
            if(onlineFriendIds != null && onlineFriendIds.size() > 0)
                for (Integer onlineFriendId : onlineFriendIds) {
                    System.out.println("Ws sending logout notification to user " + onlineFriendId);
                    messagingTemplate.convertAndSend("/topic/new_offline/" + onlineFriendId, userId);
                }
        } catch (Exception e) {
            System.out.println("Sending error message to clients");
        }
    }

    @MessageMapping("/login")
    public void loginUser(@Payload int userId)
    {
        System.out.println("Logging in user with id " + userId);
        try {
            List<Integer> onlineFriendIds = userService.userLogInOrOut(userId, false);
            if(onlineFriendIds != null && onlineFriendIds.size() > 0)
                for (Integer onlineFriendId : onlineFriendIds) {
                    System.out.println("Ws sending logout notification to user " + onlineFriendId);
                    messagingTemplate.convertAndSend("/topic/new_offline/" + onlineFriendId, userId);
                }
        } catch (Exception e) {
            System.out.println("Sending error message to clients");
        }
    }
}
