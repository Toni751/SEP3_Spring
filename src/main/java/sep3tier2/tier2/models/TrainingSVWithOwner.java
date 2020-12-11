package sep3tier2.tier2.models;

/**
 * A class for representing a training short version with the owner
 * @version 1.0
 * @author Group1
 */
public class TrainingSVWithOwner extends TrainingShortVersion
{
    private SearchBarUser owner;

    public SearchBarUser getOwner() {
        return owner;
    }
}
