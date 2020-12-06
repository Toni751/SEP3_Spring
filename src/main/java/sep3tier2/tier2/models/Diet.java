package sep3tier2.tier2.models;

import java.util.List;

public class Diet extends DietShortVersion
{
    private List<Meal> meals;

    public List<Meal> getMeals() {
        return meals;
    }
}
