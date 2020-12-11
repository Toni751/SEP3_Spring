package sep3tier2.tier2.models;

/**
 * A class for representing a diet without meals
 * @version 1.0
 * @author Group1
 */
public class DietShortVersion
{
    private int id;
    private String title;
    private boolean global;
    private String description;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isGlobal() {
        return global;
    }

    public String getDescription() {
        return description;
    }
}
