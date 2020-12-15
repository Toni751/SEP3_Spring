package sep3tier2.tier2.models.user;

/**
 * A class for representing a regular user as well as a page owner
 * @version 1.0
 * @author Group1
 */
public class User
{
    private int id;
    private String email;
    private String password;
    private String name;
    private String description;
    private String city;
    private int score;
    private Address address;
    private byte[] avatar;
    private byte[] profileBackground;
    private boolean[] userStatus;
    private int relevantFriendsNumber;
    private int rating;

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

    public byte[] getAvatar() {
        return avatar;
    }

    public byte[] getProfileBackground() {
        return profileBackground;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getRating() {
        return rating;
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

    public int getRelevantFriendsNumber() {
        return relevantFriendsNumber;
    }
}
