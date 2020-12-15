package sep3tier2.tier2.models.chat;

/**
 * A class for representing a private message between 2 users
 * @version 1.0
 * @author Group1
 */
public class Message
{
    private int id;
    private int senderId;
    private int receiverId;
    private String content;
    private String timeStamp;
    private boolean hasImage;
    private byte[] picture;

    public Message(int senderId, int receiverId, String content, String timeStamp, boolean hasImage, byte[] picture) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.timeStamp = timeStamp;
        this.hasImage = hasImage;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getContent() {
        return content;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public boolean hasImage() {
        return hasImage;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public void clearPicture() {
        picture = null;
    }
}
