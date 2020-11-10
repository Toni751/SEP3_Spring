package sep3tier2.tier2.models;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserShortVersion
{
    private int userId;
    private String userFullName;
    private String accountType;
    private byte[] avatar;

    public UserShortVersion(int userId, String userFullName, String accountType){
        this.userId = userId;
        this.userFullName = userFullName;
        this.accountType = accountType;

        try
        {
            avatar = this.getClass().getClassLoader().getResourceAsStream("avatarimages/" + userId + ".png").readAllBytes();
        }
        catch (IOException e) { e.printStackTrace(); }
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
