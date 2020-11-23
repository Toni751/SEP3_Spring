package sep3tier2.tier2.services.admin;

import sep3tier2.tier2.models.PostShortVersion;
import sep3tier2.tier2.models.UserShortVersion;

import java.util.List;

public interface AdminService
{
    List<UserShortVersion> getUsers (int limit, int offset);
    List<PostShortVersion> getPosts (int limit, int offset);
    int getTotalNumberOfModel(String model) throws Exception;
}
