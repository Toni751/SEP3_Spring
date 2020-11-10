package sep3tier2.tier2.models;

public class Administrator
{
    private int id;
    private String email;
    private String password;

    public Administrator(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
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
}
