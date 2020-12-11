package sep3tier2.tier2.models;

import java.util.List;

/**
 * A class for representing a diet which includes meals
 * @version 1.0
 * @author Group1
 */
public class Diet extends DietShortVersion
{
    private List<Meal> meals;

    public List<Meal> getMeals() {
        return meals;
    }
}
