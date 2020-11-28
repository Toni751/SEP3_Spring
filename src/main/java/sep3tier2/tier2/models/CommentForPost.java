package sep3tier2.tier2.models;

public class CommentForPost
{
    private int id;
    private int ownerId;
    private int postId;
    private String content;
    private String timeStamp;

    public CommentForPost(int ownerId, int postId, String content, String timeStamp) {
        this.ownerId = ownerId;
        this.postId = postId;
        this.content = content;
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
