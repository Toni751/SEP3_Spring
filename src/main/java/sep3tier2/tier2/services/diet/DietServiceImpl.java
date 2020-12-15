package sep3tier2.tier2.services.diet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sep3tier2.tier2.models.diet.*;
import sep3tier2.tier2.networking.diet.SocketDiet;

import java.util.List;

/**
 * The service class for handling diet requests
 * @version 1.0
 * @author Group1
 */
@Service
public class DietServiceImpl implements DietService
{
    @Autowired
    SocketDiet socketDiet;

    @Override
    public int addDiet(DietWithOwner diet) throws Exception {
        if(diet.getTitle() == null || diet.getTitle().equals(""))
            throw new Exception("Diet title cannot be empty");
        if(diet.getDescription() == null || diet.getDescription().equals(""))
            throw new Exception("Diet description cannot be empty");

        try {
            return socketDiet.addDiet(diet);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public DietWithOwner getDietById(int id) throws Exception {
        if(id <= 0)
            throw new Exception("Invalid diet id");
        try{
            return socketDiet.getDietById(id);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<DietSVWithOwner> getPublicDiets(int offset) throws Exception {
        if(offset < 0)
            throw new Exception("Invalid diets offset");
        try{
            return socketDiet.getPublicDiets(offset);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<DietShortVersion> getPrivateDietsForUser(int userId, int offset) throws Exception {
        if (userId <= 0 && offset < 0)
            throw new Exception("Invalid parameters for get private diets");
        try{
            return socketDiet.getPrivateDietsForUser(userId, offset);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void editDiet(Diet diet) throws Exception {
        try{
            socketDiet.editDiet(diet);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteDiet(int id) throws Exception {
        if(id <= 0)
            throw new Exception("Invalid diet id");
        try{
            socketDiet.deleteDiet(id);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public int addMealToDiet(int dietId, Meal meal) throws Exception {
        if(dietId <= 0)
            throw new Exception("Invalid diet id");
        try {
            return socketDiet.addMealToDiet(new MealWithDiet(meal.getId(), meal.getTitle(), meal.getCalories(), meal.getDescription(), dietId));
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void editMealInDiet(int dietId, Meal meal) throws Exception {
        if(dietId <= 0)
            throw new Exception("Invalid diet id");
        try {
            socketDiet.editMealInDiet(new MealWithDiet(meal.getId(), meal.getTitle(), meal.getCalories(), meal.getDescription(), dietId));
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteMealFromDiet(int mealId, int dietId) throws Exception {
        if(mealId <= 0 || dietId <= 0)
            throw new Exception("Invalid meal/diet id");
        try {
            socketDiet.deleteMealFromDiet(mealId, dietId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
