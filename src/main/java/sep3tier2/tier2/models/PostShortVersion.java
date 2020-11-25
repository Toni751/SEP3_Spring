package sep3tier2.tier2.models;

import java.time.LocalDate;

public class PostShortVersion
{
    private int id;
    private String title;
    private String content;
    private UserShortVersion owner;
    private byte[] picture;
    private String timeStamp;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public UserShortVersion getOwner() {
        return owner;
    }

    public byte[] getPicture() {
        return picture;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
