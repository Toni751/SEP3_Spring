package sep3tier2.tier2.models;

/**
 * A class for representing a user's login credentials
 * @version 1.0
 * @author Group1
 */
public class LoginCredentials
{
    private String email;
    private String password;

    public LoginCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
