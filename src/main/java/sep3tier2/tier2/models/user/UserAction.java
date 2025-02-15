package sep3tier2.tier2.models.user;

import sep3tier2.tier2.models.ActionType;

/**
 * A class for representing a user action
 * @version 1.0
 * @author Group1
 */
public class UserAction
{
    private int senderId;
    private String senderName;
    private int receiverId;
    private ActionType actionType;
    private Object value;

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "UserAction{" +
                "senderId=" + senderId +
                ", senderName='" + senderName + '\'' +
                ", receiverId=" + receiverId +
                ", actionType=" + actionType +
                ", value=" + value +
                '}';
    }

    public String getSenderName() {
        return senderName;
    }
}
