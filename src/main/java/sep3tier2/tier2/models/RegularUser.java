package sep3tier2.tier2.models;

import java.util.List;

public class RegularUser extends User
{
    private String city;

    public RegularUser(int id, String email, String password, String accountType, String description, List<Integer> posts,
                       List<Integer> likedPosts, String fullName, String city) {
        super(id, email, password, accountType, fullName, description, posts, likedPosts);
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}
