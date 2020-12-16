package sep3tier2.tier2.controllers.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import sep3tier2.tier2.models.user.Notification;
import sep3tier2.tier2.models.user.UserAction;
import sep3tier2.tier2.models.user.UserShortVersion;
import sep3tier2.tier2.services.user.UserService;
import java.util.List;

/**
 * WebSockets Controller for notifications-related requests
 * @version 1.0
 * @author Group1
 */
@Controller
public class NotificationsController
{
    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * Creates a friends request/follow page request and sends a notification to the corresponding receiver
     * @param userAction the user action to be created
     */
    @MessageMapping("/note")
    public void sendNotification(@Payload UserAction userAction)
    {
        try {
            int notificationId = userService.postUserAction(userAction);
            Notification notification = new Notification(notificationId, userAction.getSenderId(), userAction.getSenderName(), userAction.getReceiverId(), userAction.getActionType());
            notification.setValue((boolean)userAction.getValue());
            messagingTemplate.convertAndSend("/topic/notifications/" + userAction.getReceiverId(), notification);
        } catch (Exception e) {
           messagingTemplate.convertAndSend("/topic/errors/" + userAction.getSenderId(), userAction.getActionType() + "_ERROR");
           messagingTemplate.convertAndSend("/topic/errors/" + userAction.getReceiverId(), userAction.getActionType() + "_ERROR");
        }
    }

    /**
     * Logs out a given user and notifies all his friends
     * @param userId the id of the user to be logged out
     */
    @MessageMapping("/logout")
    public void logoutUser(@Payload int userId)
    {
        try {
            List<Integer> onlineFriendIds = userService.userLogInOrOut(userId, true);
            if(onlineFriendIds != null && onlineFriendIds.size() > 0)
                for (Integer onlineFriendId : onlineFriendIds) {
                    messagingTemplate.convertAndSend("/topic/new_offline/" + onlineFriendId, userId);
                }
        } catch (Exception e) {
            System.out.println("Sending error message to clients");
        }
    }

    /**
     * Logs in a given user and notifies all his friends
     * @param userId the id of the user to be logged in
     */
    @MessageMapping("/login")
    public void loginUser(@Payload int userId)
    {
        System.out.println("Logging in user with id " + userId);
        try {
            List<Integer> onlineFriendIds = userService.userLogInOrOut(userId, false);
            UserShortVersion user = userService.getUserShortVersionById(userId);
            if(onlineFriendIds != null && onlineFriendIds.size() > 0)
                for (Integer onlineFriendId : onlineFriendIds) {
                    messagingTemplate.convertAndSend("/topic/new_online/" + onlineFriendId, user);
                }
        } catch (Exception e) {
            System.out.println("Sending error message to clients");
        }
    }
}
