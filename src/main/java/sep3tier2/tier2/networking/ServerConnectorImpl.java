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
                System.out.println("There are " + images.size() + " images to be sent");
                List<Integer> imageSizes = new ArrayList<>();
                for (byte[] image : images) {
                    imageSizes.add(image.length);
                }

                Request informAboutImagesRequest = new Request(ActionType.HAS_IMAGES, imageSizes);
                String informAboutImagesAsJson = gson.toJson(informAboutImagesRequest);

                System.out.println("*******: " + informAboutImagesAsJson + " with size " + informAboutImagesAsJson.length());
                System.out.println("Bytes size is " + informAboutImagesAsJson.getBytes().length);

                outputStream.write(informAboutImagesAsJson.getBytes());

                String confirmationAboutImages = readFromStream(inputStream);
                System.out.println("Confirmation: " + confirmationAboutImages);
              //  Thread.sleep(100);

                for (byte[] image : images) {
                    outputStream.write(image);
                }
            }
            String requestAsJson = gson.toJson(request);

            System.out.println("********* json request: " + requestAsJson);
            //Thread.sleep(100);
            outputStream.write(requestAsJson.getBytes());

            String received = readFromStream(inputStream);
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

                String receivedRequestResponse = readFromStream(inputStream);
                System.out.println("Received " + receivedRequestResponse + " " + receivedRequestResponse.length());
                Request requestResponse = gson.fromJson(receivedRequestResponse, Request.class);
                return new ActualRequest(requestResponse, incomingImages);
            } else
                return new ActualRequest(receivedResponse, null);

        } catch (Exception e) {
            System.out.println("Sockets connection broke");
        }
        return null;
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        byte[] readBytes = new byte[65535];
        int readResultLength = inputStream.read(readBytes);
        String received = new String(readBytes, 0, readResultLength);
        System.out.println("Received " + received + " " + received.length());
        return received;
    }
}
