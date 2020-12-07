package sep3tier2.tier2.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sep3tier2.tier2.models.*;
import sep3tier2.tier2.networking.ServerConnector;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class SocketsUtilMethods
{
    private Gson gson;

    @Autowired
    private ServerConnector serverConnector;

    public SocketsUtilMethods() {
        gson = new Gson();
    }

    public List<Integer> getIntegerList(Request request)
    {
        ActualRequest response = serverConnector.requestToServer(new ActualRequest(request, null));
        if (response.getRequest().getArgument() == null)
            return null;

        Type integersListType = new TypeToken<List<Integer>>(){}.getType();
        return gson.fromJson(response.getRequest().getArgument().toString(), integersListType);
    }

    public boolean requestWithBooleanReturnTypeWithoutImages(Request request)
    {
        ActualRequest requestResponse = serverConnector.requestToServer(new ActualRequest(request, null));
        if (requestResponse == null || requestResponse.getRequest() == null)
            return false;

        Boolean bool = gson.fromJson(requestResponse.getRequest().getArgument().toString(), Boolean.class);
        System.out.println("Boolean request result is " + bool);
        return bool;
    }

    public int requestWithIntegerReturnTypeWithoutImages(Request request)
    {
        ActualRequest requestResponse = serverConnector.requestToServer(new ActualRequest(request, null));
        if (requestResponse == null || requestResponse.getRequest() == null)
            return -1;

        Integer integer = gson.fromJson(requestResponse.getRequest().getArgument().toString(), Integer.class);
        System.out.println("Integer request result is " + integer);
        return integer;
    }

    public List<UserShortVersion> requestUsersWithImages(Request request)
    {
        ActualRequest response = serverConnector.requestToServer(new ActualRequest(request, null));
        if(response == null || response.getRequest() == null)
            return null;

        List<UserShortVersion> users = new ArrayList<>();
        if(response.getRequest().getArgument() == null || response.getImages() == null)
            return users;

        Type userListType = new TypeToken<List<UserShortVersion>>(){}.getType();
        users = gson.fromJson(response.getRequest().getArgument().toString(), userListType);
        for (int i = 0; i < users.size(); i++) {
            users.get(i).setAvatar(response.getImages().get(i));
        }
        return users;
    }
}
