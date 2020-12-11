package sep3tier2.tier2.models;

/**
 * A class for representing a training with it's owner
 * @version 1.0
 * @author Group1
 */
public class TrainingWithOwner extends Training
{
    private SearchBarUser owner;

    public SearchBarUser getOwner() {
        return owner;
    }
}
