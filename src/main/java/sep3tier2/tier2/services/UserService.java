package sep3tier2.tier2.services;

import sep3tier2.tier2.models.User;

public interface UserService
{
    void addUser(User user);
    User login (String email, String password);
}
