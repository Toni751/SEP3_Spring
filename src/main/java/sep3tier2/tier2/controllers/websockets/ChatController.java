package sep3tier2.tier2.controllers.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import sep3tier2.tier2.models.*;
import sep3tier2.tier2.models.chat.Message;
import sep3tier2.tier2.models.chat.MessageWithSenderName;
import sep3tier2.tier2.models.user.Notification;
import sep3tier2.tier2.services.chat.ChatService;

import java.util.List;

/**
 * WebSockets Controller for chat-related requests
 * @version 1.0
 * @author Group1
 */
@Controller
public class ChatController
{
    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * Sends a message as well as a notification to a given user, from another user
     * @param message the message to be sent
     */
    @MessageMapping("/chat")
    public void sendMessage(@Payload MessageWithSenderName message)
    {
        try {
            Message temp = new Message(message.getSenderId(), message.getReceiverId(), message.getContent(), message.getTimeStamp(), message.hasImage(), message.getPicture());

            List<Integer> ints = chatService.addMessage(temp);
            message.setId(ints.get(0));
            Notification notification = new Notification(ints.get(1), message.getSenderId(), message.getSenderName(), message.getReceiverId(), ActionType.MESSAGE_CREATE);

            if(message.getPicture() != null && message.getPicture().length > 1 && message.getContent() == null)
                message.setContent(" ");
            messagingTemplate.convertAndSend("/topic/notifications/" + message.getReceiverId(), notification);
            messagingTemplate.convertAndSend("/topic/chat/" + message.getReceiverId(), message);

            message.setContent(null);
            message.clearPicture();
            messagingTemplate.convertAndSend("/topic/chat/" + message.getSenderId(), message);
        } catch (Exception e) {
            messagingTemplate.convertAndSend("/topic/errors/" + message.getSenderId(), e.getMessage());
            messagingTemplate.convertAndSend("/topic/errors/" + message.getReceiverId(), e.getMessage());
        }
    }

    /**
     * Deletes a given message
     * @param message the message to be deleted
     */
    @MessageMapping("/delete_message")
    public void deleteMessage(@Payload Message message)
    {
        try {
            chatService.deleteMessage(message.getId());
            message.setContent(null);
            message.clearPicture();
            messagingTemplate.convertAndSend("/topic/chat/delete" + message.getReceiverId(), message);
            messagingTemplate.convertAndSend("/topic/chat/delete" + message.getSenderId(), message);
        } catch (Exception e) {
            messagingTemplate.convertAndSend("/topic/errors/" + message.getSenderId(), e.getMessage());
            messagingTemplate.convertAndSend("/topic/errors/" + message.getReceiverId(), e.getMessage());
        }
    }
}
