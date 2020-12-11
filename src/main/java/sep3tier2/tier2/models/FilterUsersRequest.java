package sep3tier2.tier2.models;

/**
 * A class for representing a search bar request, with the sender and the query string
 * @version 1.0
 * @author Group1
 */
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
