package sep3tier2.tier2.networking;

import sep3tier2.tier2.models.User;
import sep3tier2.tier2.models.UserShortVersion;

public interface SocketClient
{
    boolean addUser(User user);
    UserShortVersion login (String email, String password);
    boolean editUser (User user);
    User getUserById(int id);
}
