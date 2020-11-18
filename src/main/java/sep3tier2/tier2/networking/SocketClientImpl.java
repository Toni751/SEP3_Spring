package sep3tier2.tier2.networking;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Component;
import sep3tier2.tier2.models.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

@Component
public class SocketClientImpl implements SocketClient
{
    private Gson gson;
    private JsonReader reader;
//    private Socket socket;
//    private InputStream inputStream;
//    private OutputStream outputStream;

    public SocketClientImpl()
    {
        gson = new Gson();
    }

    public void startClient()
    {
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
    public boolean addUser(User user)
    {
        Request request = new Request(ActionType.USER_REGISTER, user);
        Request registerResult = requestToServer(request);
        if (registerResult == null)
            return false;
        Boolean bool = gson.fromJson(registerResult.getArgument().toString(), Boolean.class);
        System.out.println("Boolean result is " + bool);
        return bool;
    }

    @Override
    public String login(String email, String password)
    {
        LoginCredentials loginCredentials = new LoginCredentials(email, password);
        Request request = new Request(ActionType.USER_LOGIN, loginCredentials);
        Request response = requestToServer(request);
        if (response == null)
            return null;
       // return gson.fromJson(response.getArgument().toString(), UserShortVersion.class);
        return response.getArgument().toString();
    }

    @Override
    public User getUserById(int id) {
        Request request = new Request(ActionType.USER_GET_BY_ID, id);
        Request userResponse = requestToServer(request);
        return gson.fromJson(userResponse.getArgument().toString(), User.class);
    }

    private Request requestToServer(Request request)
    {
        try {
            Socket socket = new Socket("localhost", 2910);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            String toWrite = gson.toJson(request);
            byte[] toSendBytes = toWrite.getBytes();
            outputStream.write(toSendBytes);
            System.out.println("Sent request to the server " + toWrite);

            byte[] readBytes = new byte[1024 * 64];
            int readResultLength = inputStream.read(readBytes);
            String received = new String(readBytes, 0, readResultLength);
            System.out.println("Received " + received + " " + received.length());
            return gson.fromJson(received, Request.class);
//            File avatar = new File("avatar.png");
//            FileOutputStream out = new FileOutputStream(avatar);
//            out.write(readBytes);
//            out.close();
//
//            System.out.println("DDDDDDDDDDDDDDDDDD "+readResultLength);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
