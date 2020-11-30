package sep3tier2.tier2.models;

import java.io.IOException;
import java.util.List;

public class User
{
    private int id;
    private String email;
    private String password;
    private String name;
    private String description;
//    private List<Post> posts; //ids of posts
    private List<Integer> likedPosts; //ids of liked posts
    private String city;
    private Address address;
    private byte[] avatar;
    private byte[] profileBackground;
    private boolean[] userStatus;

    public User(int id, String email, String password, String accountType, String name, String description, String city, Address address) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.description = description;
        this.city = city;
        this.address = address;
//        try
//        {
//            avatar = this.getClass().getClassLoader().getResourceAsStream("avatarimages/" + this.id + ".png").readAllBytes();
//            profileBackground = this.getClass().getClassLoader().getResourceAsStream("backgroundimages/" + this.id + ".jpg").readAllBytes();
//        }
//        catch (IOException e) { e.printStackTrace(); }
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

    public String getDescription() {
        return description;
    }
//
//    public List<Integer> getPosts() {
//        return posts;
//    }
//
//    public List<Integer> getLikedPosts() {
//        return likedPosts;
//    }
//
    public byte[] getAvatar() {
        return avatar;
    }

    public byte[] getProfileBackground() {
        return profileBackground;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public Address getAddress() {
        return address;
    }

    public void clearProfileBackground()
    {
        profileBackground = null;
    }

    public void clearAvatar()
    {
        avatar = null;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public void setProfileBackground(byte[] profileBackground) {
        this.profileBackground = profileBackground;
    }

    public boolean[] getUserStatus() {
        return userStatus;
    }
}
