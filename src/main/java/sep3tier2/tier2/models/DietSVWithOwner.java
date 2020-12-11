package sep3tier2.tier2.models;

/**
 * A class for representing a diet without meals and with owner
 * @version 1.0
 * @author Group1
 */
public class DietSVWithOwner extends DietShortVersion
{
    private SearchBarUser owner;

    public SearchBarUser getOwner() {
        return owner;
    }
}
