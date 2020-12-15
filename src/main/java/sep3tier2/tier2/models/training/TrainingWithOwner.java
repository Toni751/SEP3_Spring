package sep3tier2.tier2.models.training;

import sep3tier2.tier2.models.user.SearchBarUser;

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
