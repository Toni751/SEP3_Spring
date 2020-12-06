package sep3tier2.tier2.models;

public class Meal
{
    private int id;
    private String title;
    private int calories;
    private String description;

    public Meal(int id, String title, int calories, String description) {
        this.id = id;
        this.title = title;
        this.calories = calories;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCalories() {
        return calories;
    }

    public String getDescription() {
        return description;
    }
}
