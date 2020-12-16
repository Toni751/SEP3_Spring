package sep3tier2.tier2.networking;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import sep3tier2.tier2.models.ActionType;
import sep3tier2.tier2.models.ActualRequest;
import sep3tier2.tier2.models.Request;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for establishing, maintaining and closing sockets connections
 * @version 1.0
 * @author Group1
 */
@Component
public class ServerConnectorImpl implements ServerConnector
{
    @Override
    public ActualRequest requestToServer(ActualRequest actualRequest) {
        try {
            Socket socket = new Socket("localhost", 2910);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            Gson gson = new Gson();

            Request request = actualRequest.getRequest();
            List<byte[]> images = actualRequest.getImages();

            if (images != null && !images.isEmpty()) {
                List<Integer> imageSizes = new ArrayList<>();
                for (byte[] image : images) {
                    imageSizes.add(image.length);
                }

                Request informAboutImagesRequest = new Request(ActionType.HAS_IMAGES, imageSizes);
                String informAboutImagesAsJson = gson.toJson(informAboutImagesRequest);
                outputStream.write(informAboutImagesAsJson.getBytes());

                String confirmationAboutImages = readFromStream(inputStream);

                for (byte[] image : images) {
                    outputStream.write(image);
                }
            }
            String requestAsJson = gson.toJson(request);
            outputStream.write(requestAsJson.getBytes());

            String received = readFromStream(inputStream);
            Request receivedResponse = gson.fromJson(received, Request.class);

            if (receivedResponse.getActionType().equals(ActionType.HAS_IMAGES)) {
                List<Double> incomingImageSizesAsDoubles = (List<Double>) receivedResponse.getArgument();
                List<Integer> incomingImageSizes = new ArrayList<>();
                for (Double sizesAsDouble : incomingImageSizesAsDoubles) {
                    incomingImageSizes.add(sizesAsDouble.intValue());
                }
                List<byte[]> incomingImages = new ArrayList<>();
                byte[] temp;
                for (Integer incomingImageSize : incomingImageSizes) {
                    temp = inputStream.readNBytes(incomingImageSize);
                    incomingImages.add(temp);
                }

                String receivedRequestResponse = readFromStream(inputStream);
                Request requestResponse = gson.fromJson(receivedRequestResponse, Request.class);
                return new ActualRequest(requestResponse, incomingImages);
            } else
                return new ActualRequest(receivedResponse, null);

        } catch (Exception e) {
            System.out.println("Sockets connection broke");
        }
        return null;
    }

    /**
     * Private method for reading input from the stream
     * @param inputStream the input stream to read the input
     * @return the bytes read converted to a string
     * @throws IOException if no input could be read from the stream
     */
    private String readFromStream(InputStream inputStream) throws IOException {
        byte[] readBytes = new byte[65535];
        int readResultLength = inputStream.read(readBytes);
        String received = new String(readBytes, 0, readResultLength);
        return received;
    }
}
