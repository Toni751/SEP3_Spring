package sep3tier2.tier2.networking;

import sep3tier2.tier2.models.User;

public interface SocketClient
{
    void addUser(User user);
    void login (String email, String password);
}
