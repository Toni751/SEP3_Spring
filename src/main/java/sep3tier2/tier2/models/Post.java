package sep3tier2.tier2.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Post
{
    private int id;
    private String title;
    private UserShortVersion owner;
    private byte[] picture;
    private LocalDate timeStamp;
    private List<Comment> comments;
    private List<Integer> userIdsForLikes;

    public Post(int id, String title, UserShortVersion owner, LocalDate timeStamp) {
        this.id = id;
        this.title = title;
        this.owner = owner;
        this.timeStamp = timeStamp;
        comments = new ArrayList<>();
        userIdsForLikes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public UserShortVersion getOwner() {
        return owner;
    }

    public byte[] getPicture() {
        return picture;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Integer> getUserIdsForLikes() {
        return userIdsForLikes;
    }
}
