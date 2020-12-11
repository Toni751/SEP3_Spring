package sep3tier2.tier2.models;

/**
 * A class for representing a private message, with the sender's name included
 * @version 1.0
 * @author Group1
 */
public class MessageWithSenderName extends Message
{
    private String senderName;

    public MessageWithSenderName(int senderId, int receiverId, String content, String timeStamp, boolean hasImage, byte[] picture) {
        super(senderId, receiverId, content, timeStamp, hasImage, picture);
    }

    public String getSenderName() {
        return senderName;
    }
}
