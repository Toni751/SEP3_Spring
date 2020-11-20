package sep3tier2.tier2.networking;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Component;
import sep3tier2.tier2.models.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Component
public class SocketClientImpl implements SocketClient {
    private Gson gson;
    private JsonReader reader;
//    private Socket socket;
//    private InputStream inputStream;
//    private OutputStream outputStream;

    public SocketClientImpl() {
        gson = new Gson();
    }

    public void startClient() {
//        try {
////            socket = new Socket("localhost", 2910);
////            inputStream = socket.getInputStream();
////            outputStream = socket.getOutputStream();
////            new Thread(this::listenToServer).start();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }

    }

//    private void listenToServer() {
//        byte[] receivedBytes = new byte[1024];
//        try {
//            inputStream.read(receivedBytes);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String received = new String(receivedBytes);
//        String updatedReceive = "";
//        for (int i = 0; i < received.length(); i++) {
//            if (received.charAt(i) == 0)
//                break;
//            updatedReceive += received.charAt(i);
//        }
//        System.out.println("Received " + updatedReceive + " "+ updatedReceive.length());
//    }

    @Override
    public boolean addUser(User user) {
        Request request = new Request(ActionType.USER_REGISTER, user);
        ActualRequest registerResult = requestToServer(new ActualRequest(request, null));
        if (registerResult == null)
            return false;

        Boolean bool = gson.fromJson(registerResult.getRequest().getArgument().toString(), Boolean.class);
        System.out.println("Boolean result is " + bool);
        return bool;
    }

    @Override
    public UserShortVersion login(String email, String password) {
        LoginCredentials loginCredentials = new LoginCredentials(email, password);
        Request request = new Request(ActionType.USER_LOGIN, loginCredentials);
        ActualRequest response = requestToServer(new ActualRequest(request, null));
        if (response == null)
            return null;
        // return gson.fromJson(response.getArgument().toString(), UserShortVersion.class);
        UserShortVersion responseArgument = gson.fromJson(response.getRequest().getArgument().toString(), UserShortVersion.class);
        if (response.getImages() != null) {
            responseArgument.setAvatar(response.getImages().get(0));
        }
        return responseArgument;
    }

    @Override
    public boolean editUser(User user) {
        List<byte[]> userImages = new ArrayList<>();
        if (user.getAvatar() != null) {
            userImages.add(user.getAvatar());
            user.clearAvatar();
        }
        if (user.getProfileBackground() != null) {
            userImages.add(user.getProfileBackground());
            user.clearProfileBackground();
        }
        Request request = new Request(ActionType.USER_EDIT, user);
        ActualRequest actualRequest = new ActualRequest(request, userImages);
        ActualRequest response = requestToServer(actualRequest);

        Boolean bool = gson.fromJson(response.getRequest().getArgument().toString(), Boolean.class);
        System.out.println("Boolean edit result is " + bool);
        return bool;
    }

    @Override
    public User getUserById(int id) {
        Request request = new Request(ActionType.USER_GET_BY_ID, id);
        ActualRequest userResponse = requestToServer(new ActualRequest(request, null));

        User user = gson.fromJson(userResponse.getRequest().getArgument().toString(), User.class);;
        if (userResponse.getImages() != null)
        {
            user.setAvatar(userResponse.getImages().get(0));
            if (userResponse.getImages().size() == 2)
                user.setProfileBackground(userResponse.getImages().get(1));
        }
        return user;
    }

    private ActualRequest requestToServer(ActualRequest actualRequest) {
        try {
            Socket socket = new Socket("localhost", 2910);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            Request request = actualRequest.getRequest();
            List<byte[]> images = actualRequest.getImages();

            if (images != null && !images.isEmpty()) {
                System.out.println("There are " + images.size() + " images to be sent");
                List<Integer> imageSizes = new ArrayList<>();
                for (byte[] image : images) {
                    imageSizes.add(image.length);
                }

                Request informAboutImagesRequest = new Request(ActionType.HAS_IMAGES, imageSizes);
                String informAboutImagesAsJson = gson.toJson(informAboutImagesRequest);
                outputStream.write(informAboutImagesAsJson.getBytes());

                for (byte[] image : images) {
                    outputStream.write(image);
                }
            }
            String requestAsJson = gson.toJson(request);
            outputStream.write(requestAsJson.getBytes());

            byte[] readBytes = new byte[65535];
            int readResultLength = inputStream.read(readBytes);
            String received = new String(readBytes, 0, readResultLength);
            System.out.println("Received " + received + " " + received.length());
            Request receivedResponse = gson.fromJson(received, Request.class);

            if (receivedResponse.getActionType().equals(ActionType.HAS_IMAGES)) {
                List<Double> incomingImageSizesAsDoubles = (List<Double>) receivedResponse.getArgument();
                List<Integer> incomingImageSizes = new ArrayList<>();
                for (Double sizesAsDouble : incomingImageSizesAsDoubles) {
                    incomingImageSizes.add(sizesAsDouble.intValue());
                }
                System.out.println("Going to receive " + incomingImageSizes.size() + " images");
                List<byte[]> incomingImages = new ArrayList<>();
                byte[] temp;
                for (Integer incomingImageSize : incomingImageSizes) {
                    temp = inputStream.readNBytes(incomingImageSize);
                    System.out.println("Read image with length " + temp.length);
                    incomingImages.add(temp);
                }

                byte[] readResponseRequestBytes = new byte[65535];
                int readRequestResultLength = inputStream.read(readResponseRequestBytes);
                System.out.println("Read request length after receiving images " + readRequestResultLength);
                String receivedRequestResponse = new String(readResponseRequestBytes, 0, readRequestResultLength);
                System.out.println("Received " + receivedRequestResponse + " " + receivedRequestResponse.length());
                Request requestResponse = gson.fromJson(receivedRequestResponse, Request.class);
                return new ActualRequest(requestResponse, incomingImages);
            } else
                return new ActualRequest(receivedResponse, null);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
