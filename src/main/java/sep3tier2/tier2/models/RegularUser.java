package sep3tier2.tier2.models;

public class RegularUser extends User
{
    private String firstName;
    private String lastName;
    private String city;

    public RegularUser(int id, String email, String password, String accountType, String firstName, String lastName, String city) {
        super(id, email, password, accountType);
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }
}
