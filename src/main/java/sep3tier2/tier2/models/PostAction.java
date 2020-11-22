package sep3tier2.tier2.models;

public class PostAction
{
    private int userId;
    private int postId;
    private ActionType actionType;
    private boolean value;

    public int getUserId() {
        return userId;
    }

    public int getPostId() {
        return postId;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public boolean getValue() {
        return value;
    }
}
