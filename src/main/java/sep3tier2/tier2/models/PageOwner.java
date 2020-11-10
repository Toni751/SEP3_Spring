package sep3tier2.tier2.models;

public class PageOwner extends User
{
    private String name;
    private Address address;

    public PageOwner(int id, String email, String password, String accountType, String name, Address address) {
        super(id, email, password, accountType);
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }
}
