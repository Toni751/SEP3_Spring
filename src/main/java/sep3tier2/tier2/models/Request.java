package sep3tier2.tier2.models;

public class Request
{
    private ActionType actionType;
    private Object argument;

    public Request(ActionType actionType, Object argument) {
        this.actionType = actionType;
        this.argument = argument;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public Object getArgument() {
        return argument;
    }
}
