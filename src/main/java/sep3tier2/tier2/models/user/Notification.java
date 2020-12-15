package sep3tier2.tier2.models.user;

import sep3tier2.tier2.models.ActionType;

/**
 * A class for representing a notification
 * @version 1.0
 * @author Group1
 */
public class Notification
{
    private int id;
    private int senderId;
    private String senderName;
    private int receiverId;
    private ActionType actionType;
    private boolean value;

    public Notification(int id, int senderId, String senderName, int receiverId, ActionType actionType) {
        this.id = id;
        this.senderId = senderId;
        this.senderName = senderName;
        this.receiverId = receiverId;
        this.actionType = actionType;
    }

    public int getId() {
        return id;
    }

    public int getSenderId() {
        return senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public ActionType getActionType() {
        return actionType;
    }
}
