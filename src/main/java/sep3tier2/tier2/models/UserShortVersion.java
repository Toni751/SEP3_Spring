package sep3tier2.tier2.models;

public class UserShortVersion
{
    private int userId;
    private String userFullName;
    private String accountType;
    private byte[] avatar;

    public UserShortVersion(int userId, String userFullName, String accountType) {
        this.userId = userId;
        this.userFullName = userFullName;
        this.accountType = accountType;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public String getAccountType() {
        return accountType;
    }

    public byte[] getAvatar() {
        return avatar;
    }
}
