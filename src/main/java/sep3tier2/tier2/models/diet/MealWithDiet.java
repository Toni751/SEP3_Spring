package sep3tier2.tier2.models.diet;

/**
 * A class for representing a meal with the diet it belongs to
 * @version 1.0
 * @author Group1
 */
public class MealWithDiet extends Meal
{
    private int dietId;

    public MealWithDiet(int id, String title, int calories, String description, int dietId) {
        super(id, title, calories, description);
        this.dietId = dietId;
    }

    public int getDietId() {
        return dietId;
    }
}
