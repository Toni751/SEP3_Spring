package sep3tier2.tier2.services.diet;

import sep3tier2.tier2.models.*;

import java.util.List;

/**
 * The interface for the service class which manages diet actions
 * @version 1.0
 * @author Group1
 */
public interface DietService
{
    /**
     * Adds a given diet to a given user
     * @param diet the diet to be added, with the owner id
     * @return the id of the created diet
     */
    int addDiet(DietWithOwner diet) throws Exception;

    /**
     * Returns a diet by its id, or null if it doesn't exist
     * @param id the id of the diet
     * @return the diet at the given id
     */
    DietWithOwner getDietById(int id) throws Exception;

    /**
     * Returns a list of public diets, by skipping the first "offset" public diets
     * @param offset the number of public diets to be skipped
     * @return the list of diets, without meals and with owners
     */
    List<DietSVWithOwner> getPublicDiets(int offset) throws Exception;

    /**
     * Returns a list of private diets belonging to a given user, skipping the first "offset" private diets
     * @param userId the id of the user
     * @param offset the number of private diets to be skipped
     * @return the list of the user's private diets, without meals
     */
    List<DietShortVersion> getPrivateDietsForUser(int userId, int offset) throws Exception;

    /**
     * Edits a diet at a given id
     * @param diet the new value for the diet
     */
    void editDiet(Diet diet) throws Exception;

    /**
     * Deletes a diet at a given id
     * @param id the id of the diet to be deleted
     */
    void deleteDiet(int id) throws Exception;

    /**
     * Adds a given meal to a given diet
     * @param meal the meal to be added
     * @return the id of the created meal
     */
    int addMealToDiet(int dietId, Meal meal) throws Exception;

    /**
     * Edits a given meal in a specific diet
     * @param meal the new value for the meal
     */
    void editMealInDiet(int dietId, Meal meal) throws Exception;

    /**
     * Deletes a meal at a given id from a given diet
     * @param dietId the id of the diet
     * @param mealId the id of th meal to be deleted
     */
    void deleteMealFromDiet(int mealId, int dietId) throws Exception;
}
