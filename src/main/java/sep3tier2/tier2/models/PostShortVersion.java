package sep3tier2.tier2.models;

import java.time.LocalDate;
import java.util.Date;

public class PostShortVersion
{
    private int id;
    private String title;
    private String content;
    private UserShortVersion owner;
    private byte[] picture;
    private String timeStamp;
    private int numberOfComments;
    private int numberOfLikes;
    private boolean hasImage;
    private boolean[] postStatus;

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

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }
    public boolean hasImage(){
        return hasImage;
    }
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public void clearPicture() {
        picture = null;
    }

    public boolean[] getPostStatus() {
        return postStatus;
    }
}
