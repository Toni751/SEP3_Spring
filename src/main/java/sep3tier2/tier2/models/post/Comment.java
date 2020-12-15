package sep3tier2.tier2.models.post;

import sep3tier2.tier2.models.user.UserShortVersion;

/**
 * A class for representing a comment on a post
 * @version 1.0
 * @author Group1
 */
public class Comment
{
    private int id;
    private UserShortVersion owner;
    private String content;
    private String timeStamp;

//    public Comment(UserShortVersion owner, String content, String timeStamp) {
//        this.owner = owner;
//        this.content = content;
//        this.timeStamp = timeStamp;
//    }

    public int getId() {
        return id;
    }

    public UserShortVersion getOwner() {
        return owner;
    }

    public String getContent() {
        return content;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
