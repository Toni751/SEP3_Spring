package sep3tier2.tier2.models;

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
