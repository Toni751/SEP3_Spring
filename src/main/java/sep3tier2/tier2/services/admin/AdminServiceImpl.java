package sep3tier2.tier2.services.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sep3tier2.tier2.models.PostShortVersion;
import sep3tier2.tier2.models.UserShortVersion;
import sep3tier2.tier2.networking.admin.SocketAdmin;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService
{
    @Autowired
    SocketAdmin socketAdmin;

    @Override
    public List<UserShortVersion> getUsers(int limit, int offset) {
        return socketAdmin.getUsers(limit, offset);
    }

    @Override
    public List<Integer> getPosts(int limit, int offset) {
        return socketAdmin.getPosts(limit, offset);
    }

    @Override
    public int getTotalNumberOfModel(String model) throws Exception{
        if (!model.equals("users") && !model.equals("posts"))
        {
            throw new Exception("Invalid mode");
        }
        return socketAdmin.getTotalNumberOfModel(model);
    }
}
