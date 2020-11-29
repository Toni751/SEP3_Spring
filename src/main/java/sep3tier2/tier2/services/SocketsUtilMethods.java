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
    private Gson gson;

    @Autowired
    private ServerConnector serverConnector;

    public SocketsUtilMethods() {
        gson = new Gson();
    }

    public List<Integer> getPosts(Request request)
    {
        ActualRequest response = serverConnector.requestToServer(new ActualRequest(request, null));
        if (response.getRequest().getArgument() == null)
            return null;
        Type postListType = new TypeToken<List<Integer>>(){}.getType();
        //        if (response.getImages() != null && !response.getImages().isEmpty())
//        {
//            List<byte[]> images = response.getImages();
//            int imageCounter = 0;
//
//            for (PostShortVersion post : posts) {
//                post.getOwner().setAvatar(images.get(imageCounter++));
//            }
//
//            for (PostShortVersion post : posts) {
//                if (post.hasImage())
//                    post.setPicture(images.get(imageCounter++));
//            }
//        }

        return gson.fromJson(response.getRequest().getArgument().toString(), postListType);
    }

    public boolean requestWithBooleanReturnTypeWithoutImages(Request request)
    {
        System.out.println("Heeerrreeeee");
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
}
