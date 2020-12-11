package sep3tier2.tier2.networking.admin;

import sep3tier2.tier2.models.PostShortVersion;
import sep3tier2.tier2.models.UserShortVersion;

import java.util.List;

/**
 * The interface for the socket class which manages administrator actions
 * @version 1.0
 * @author Group1
 */
public interface SocketAdmin
{
    /**
     * Returns a list of "limit" number of reported users, by skipping the first "offset" number of users
     * @param limit the number of reported users to be returned
     * @param offset the number of reported users to be skipped
     * @return the list of reported users, ordered in descending order by the total number of reports, as user short version objects
     */
    List<UserShortVersion> getUsers (int limit, int offset);

    /**
     * Returns a list of "limit" number of reported posts, by skipping the first "offset" number of posts
     * @param limit the number of reported posts to be returned
     * @param offset the number of reported posts to be skipped
     * @return the list of reported post ids, ordered in descending order by the total number of reports
     */
    List<Integer> getPosts (int limit, int offset);

    /**
     * Returns the total number of users/posts in the system
     * @param model the given model, can be "users" or "posts"
     * @return the total number of users/posts in the system
     */
    int getTotalNumberOfModel(String model);
}
