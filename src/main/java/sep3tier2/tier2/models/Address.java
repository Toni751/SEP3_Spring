package sep3tier2.tier2.models;

public class Address
{
    private int id;
    private String street;
    private String number;

    public Address(String street, String number) {
        this.street = street;
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
