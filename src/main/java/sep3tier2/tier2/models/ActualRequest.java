package sep3tier2.tier2.models;

import java.util.List;

/**
 * A class for representing an sockets request, made by a regular request object and an array of images
 * @version 1.0
 * @author Group1
 */
public class ActualRequest {

    private Request request;
    private List<byte[]> images;

    public ActualRequest(Request request, List<byte[]> images) {
        this.request = request;
        this.images = images;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public List<byte[]> getImages() {
        return images;
    }

    public void setImages(List<byte[]> images) {
        this.images = images;
    }
}
