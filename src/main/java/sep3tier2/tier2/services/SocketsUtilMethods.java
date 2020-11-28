package sep3tier2.tier2.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sep3tier2.tier2.models.ActualRequest;
import sep3tier2.tier2.models.PostShortVersion;
import sep3tier2.tier2.models.Request;
import sep3tier2.tier2.networking.ServerConnector;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class SocketsUtilMethods
{
    private static Gson gson;

    @Autowired
    static ServerConnector serverConnector;

    public SocketsUtilMethods() {
        gson = new Gson();
    }

    public static List<PostShortVersion> getPosts(Request request)
    {
        ActualRequest response = serverConnector.requestToServer(new ActualRequest(request, null));
        if (response.getRequest().getArgument() == null)
            return null;
        Type postListType = new TypeToken<List<PostShortVersion>>(){}.getType();
        List<PostShortVersion> posts = gson.fromJson(response.getRequest().getArgument().toString(), postListType);
        if (response.getImages() != null && !response.getImages().isEmpty())
        {
            int imageCounter = 0;
            for (PostShortVersion post : posts) {
                if (post.hasImage())
                    post.setPicture(response.getImages().get(imageCounter++));
            }
        }

        return posts;
    }

    public static boolean requestWithBooleanReturnTypeWithoutImages(Request request)
    {
        ActualRequest requestResponse = serverConnector.requestToServer(new ActualRequest(request, null));
        if (requestResponse == null || requestResponse.getRequest() == null)
            return false;

        Boolean bool = gson.fromJson(requestResponse.getRequest().getArgument().toString(), Boolean.class);
        System.out.println("Boolean request result is " + bool);
        return bool;
    }

    public static int requestWithIntegerReturnTypeWithoutImages(Request request)
    {
        ActualRequest requestResponse = serverConnector.requestToServer(new ActualRequest(request, null));
        if (requestResponse == null || requestResponse.getRequest() == null)
            return -1;

        Integer integer = gson.fromJson(requestResponse.getRequest().getArgument().toString(), Integer.class);
        System.out.println("Integer request result is " + integer);
        return integer;
    }
}
