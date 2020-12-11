package sep3tier2.tier2.models;

/**
 * A class for representing a sockets request, with a type and an argument
 * @version 1.0
 * @author Group1
 */
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
