package sep3tier2.tier2.networking.admin;

import sep3tier2.tier2.models.PostShortVersion;
import sep3tier2.tier2.models.UserShortVersion;

import java.util.List;

public interface SocketAdmin
{
    List<UserShortVersion> getUsers (int limit, int offset);
    List<Integer> getPosts (int limit, int offset);
    int getTotalNumberOfModel(String model);
}
