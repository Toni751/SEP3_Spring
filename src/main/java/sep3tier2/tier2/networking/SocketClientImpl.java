package sep3tier2.tier2.networking;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import sep3tier2.tier2.models.Login;
import sep3tier2.tier2.models.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@Component
public class SocketClientImpl implements SocketClient
{
    private Gson gson;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public SocketClientImpl()
    {
        gson = new Gson();
    }

    public void startClient()
    {
        try {
            socket = new Socket("localhost", 2910);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            new Thread(this::listenToServer).start();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void listenToServer() {
        byte[] receivedBytes = new byte[1024];
        try {
            inputStream.read(receivedBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String received = new String(receivedBytes);
        String updatedReceive = "";
        for (int i = 0; i < received.length(); i++) {
            if (received.charAt(i) == 0)
                break;
            updatedReceive += received.charAt(i);
        }
        System.out.println("Received " + updatedReceive + " "+ updatedReceive.length());
    }

    @Override
    public void addUser(User user)
    {
        String userAsJson = gson.toJson(user);
        System.out.println("User as json " + userAsJson);
        writeToServer(userAsJson);
    }

    @Override
    public void login(String email, String password)
    {
        Login login = new Login(email, password);
        String loginAsJson = gson.toJson(login);
        System.out.println("Login as json " + loginAsJson);
        writeToServer(loginAsJson);

    }

    private void writeToServer(String toWrite)
    {
        byte[] toSendBytes = toWrite.getBytes();
        try {
            outputStream.write(toSendBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
