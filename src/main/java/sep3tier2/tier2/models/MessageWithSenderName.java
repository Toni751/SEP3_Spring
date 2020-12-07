package sep3tier2.tier2.models;

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
