package sep3tier2.tier2.models.user;

/**
 * A class for representing a user short version with the fitness status in rapport with another user (if trainings/diets were shared or not)
 * @version 1.0
 * @author Group1
 */
public class UserShortVersionWithStatus extends UserShortVersion
{
    private boolean[] status;

    public boolean[] getStatus() {
        return status;
    }
}
