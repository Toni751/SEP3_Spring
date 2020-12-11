package sep3tier2.tier2.models;

/**
 * A class for representing the short version of a user with the last message he/she sent to a given user
 * @version 1.0
 * @author Group1
 */
public class UserShortVersionWithMessage extends UserShortVersion
{
    private Message message;

    public Message getMessage() {
        return message;
    }
}
