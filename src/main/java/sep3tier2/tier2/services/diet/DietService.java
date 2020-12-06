package sep3tier2.tier2.services.diet;

import sep3tier2.tier2.models.*;

import java.util.List;

public interface DietService
{
    int addDiet(DietWithOwner diet) throws Exception;
    DietWithOwner getDietById(int id) throws Exception;
    List<DietSVWithOwner> getPublicDiets(int offset) throws Exception;
    List<DietShortVersion> getPrivateDietsForUser(int userId, int offset) throws Exception;
    void editDiet(Diet diet) throws Exception;
    void deleteDiet(int id) throws Exception;
    int addMealToDiet(int dietId, Meal meal) throws Exception;
    void editMealInDiet(int dietId, Meal meal) throws Exception;
    void deleteMealFromDiet(int mealId, int dietId) throws Exception;
}
