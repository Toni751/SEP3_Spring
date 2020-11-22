package sep3tier2.tier2.models;

public class UserAction
{
    private int senderId;
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
}
