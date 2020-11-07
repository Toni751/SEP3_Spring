package sep3tier2.tier2.models;

public class User
{
    private int id;
    private String email;
    private String password;
    private String accountType;

    public User(int id, String email, String password, String accountType) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
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
}
