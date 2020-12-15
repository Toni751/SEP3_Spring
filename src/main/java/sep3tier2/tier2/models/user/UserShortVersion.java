package sep3tier2.tier2.models.user;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A class for representing the short version of a user
 * @version 1.0
 * @author Group1
 */
public class UserShortVersion
{
    private int userId;
    private String userFullName;
    private String accountType;
    private transient byte[] avatar;

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

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }
}
