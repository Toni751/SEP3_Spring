package sep3tier2.tier2.controllers.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import sep3tier2.tier2.models.*;
import sep3tier2.tier2.services.chat.ChatService;
import sep3tier2.tier2.services.user.UserService;

import java.util.List;

@Controller
public class ChatController
{
//    DONE: List of online friends
//    DONE: WebSocketsNotification for incoming message
//    DONE: Create message, Delete (sets the content to null), Picture
//    DONE: GetLastMessageWithUsers(with offset)
//    DONE: GetLatestMessagesWithFriend(friendId, offset)

    // DONE: something for user logout and something about new message notifications (in db)
    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void sendMessage(@Payload MessageWithSenderName message)
    {
        System.out.println("Got message " + message.getContent());
        try {
            List<Integer> ints = chatService.addMessage(message);
            message.setId(ints.get(0));
            Notification notification = new Notification(ints.get(1), message.getSenderId(), message.getSenderName(), message.getReceiverId(), ActionType.MESSAGE_CREATE);

            messagingTemplate.convertAndSend("/topic/notifications/" + message.getReceiverId(), notification);
            messagingTemplate.convertAndSend("/topic/chat/" + message.getReceiverId(), message);

            message.setContent(null);
            message.clearPicture();
            messagingTemplate.convertAndSend("/topic/chat/" + message.getSenderId(), message);
        } catch (Exception e) {
            System.out.println("Sending error message to clients");
            messagingTemplate.convertAndSend("/topic/errors/" + message.getSenderId(), e.getMessage());
            messagingTemplate.convertAndSend("/topic/errors/" + message.getReceiverId(), e.getMessage());
        }
    }

    @MessageMapping("/delete_message")
    public void deleteMessage(@Payload Message message)
    {
        System.out.println("Deleting message with id " + message.getId());
        try {
            chatService.deleteMessage(message.getId());
            message.setContent(null);
            message.clearPicture();
            messagingTemplate.convertAndSend("/topic/chat/delete" + message.getReceiverId(), message);
            messagingTemplate.convertAndSend("/topic/chat/delete" + message.getSenderId(), message);
        } catch (Exception e) {
            System.out.println("Sending error message to clients");
            messagingTemplate.convertAndSend("/topic/errors/" + message.getSenderId(), e.getMessage());
            messagingTemplate.convertAndSend("/topic/errors/" + message.getReceiverId(), e.getMessage());
        }
    }
}
