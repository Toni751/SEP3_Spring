package sep3tier2.tier2.models.training;

/**
 * A class for representing a physical exercise
 * @version 1.0
 * @author Group1
 */
public class Exercise
{
    private int id;
    private String title;
    private String description;

    public Exercise(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
