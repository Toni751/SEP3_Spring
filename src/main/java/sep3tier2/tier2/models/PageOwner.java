package sep3tier2.tier2.models;

import java.util.List;

public class PageOwner extends User
{
    private Address address;

    public PageOwner(int id, String email, String password, String accountType, String description, List<Integer> posts,
                     List<Integer> likedPosts, String name, Address address)
    {
        super(id, email, password, accountType, name, description, posts, likedPosts);
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}
