package sep3tier2.tier2.models;

import java.util.List;

public abstract class User
{
    private int id;
    private String email;
    private String password;
    private String accountType;
    private String name;
    private String description;
    private List<Integer> posts; //ids of posts
    private List<Integer> likedPosts; //ids of liked posts
    private byte[] avatar;
    private byte[] profileBackground;

    public User(int id, String email, String password, String accountType, String name, String description,
                List<Integer> posts, List<Integer> likedPosts) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
        this.name = name;
        this.description = description;
        this.posts = posts;
        this.likedPosts = likedPosts;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getDescription() {
        return description;
    }

    public List<Integer> getPosts() {
        return posts;
    }

    public List<Integer> getLikedPosts() {
        return likedPosts;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public byte[] getProfileBackground() {
        return profileBackground;
    }

    public String getName() {
        return name;
    }
}
