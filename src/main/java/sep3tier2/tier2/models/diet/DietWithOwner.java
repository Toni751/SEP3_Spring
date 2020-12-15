package sep3tier2.tier2.models.diet;

import sep3tier2.tier2.models.user.SearchBarUser;

/**
 * A class for representing a diet with meals and with owner
 * @version 1.0
 * @author Group1
 */
public class DietWithOwner extends Diet
{
    private SearchBarUser owner;

    public SearchBarUser getOwner() {
        return owner;
    }
}
