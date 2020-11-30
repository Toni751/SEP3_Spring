package sep3tier2.tier2.models;

public class FilterUsersRequest
{
    private int senderId;
    private String queryString;

    public int getSenderId() {
        return senderId;
    }

    public String getQueryString() {
        return queryString;
    }
}
