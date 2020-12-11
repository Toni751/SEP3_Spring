package sep3tier2.tier2.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sep3tier2.tier2.models.*;
import sep3tier2.tier2.services.diet.DietService;

import java.util.List;

/**
 * Rest API Controller for diet-related requests at the endpoint "/diets"
 * @version 1.0
 * @author Group1
 */
@RestController
@RequestMapping("/diets")
public class DietsController
{
    @Autowired
    DietService dietService;

    /**
     * Adds a given diet to a given user
     * @param diet the diet to be added, with the owner id
     * @return the id of the created diet
     */
    @CrossOrigin(origins = "*")
    @PostMapping
    public @ResponseBody int addDiet(@RequestBody DietWithOwner diet) {
        System.out.println("Controller adding diet");
        try{
            return dietService.addDiet(diet);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Returns a diet by its id, or null if it doesn't exist
     * @param id the id of the diet
     * @return the diet at the given id
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public @ResponseBody DietWithOwner getDietById(@PathVariable int id) {
        System.out.println("Controller getting diet by id " + id);
        try{
            return dietService.getDietById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Returns a list of public diets, by skipping the first "offset" public diets
     * @param offset the number of public diets to be skipped
     * @return the list of diets, without meals and with owners
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/public")
    public @ResponseBody List<DietSVWithOwner> getPublicDiets(@RequestParam("offset") int offset){
        System.out.println("Getting public diets with offset " + offset);
        try{
            return dietService.getPublicDiets(offset);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Returns a list of private diets belonging to a given user, skipping the first "offset" private diets
     * @param userId the id of the user
     * @param offset the number of private diets to be skipped
     * @return the list of the user's private diets, without meals
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/private")
    public @ResponseBody List<DietShortVersion> getPrivateDietsForUser(@RequestParam("userId") int userId, @RequestParam("offset") int offset){
        System.out.println("Getting public diets with offset " + offset);
        try{
            return dietService.getPrivateDietsForUser(userId, offset);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Edits a diet at a given id
     * @param id the id of the diet to be edited
     * @param diet the new value for the diet
     * @return Ok if the action was successful, Bad_Request otherwise
     */
    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public HttpStatus editDiet(@PathVariable int id, @RequestBody Diet diet) {
        System.out.println("Controller editing diet with id " + id);
        try {
            dietService.editDiet(diet);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Deletes a diet at a given id
     * @param id the id of the diet to be deleted
     * @return Ok if the action was successful, Bad_Request otherwise
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public HttpStatus deleteDiet(@PathVariable int id) {
        System.out.println("Controller deleting diet with id " + id);
        try {
            dietService.deleteDiet(id);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Adds a given meal to a given diet
     * @param meal the meal to be added
     * @param id the id of the diet
     * @return the id of the created meal
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/{id}/meals")
    public @ResponseBody int addMealToDiet(@PathVariable int id, @RequestBody Meal meal) {
        System.out.println("Controller adding meal to diet " + id);
        try{
            return dietService.addMealToDiet(id, meal);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Edits a given meal in a specific diet
     * @param mealId the id of the meal to be edited
     * @param dietId the id of the diet
     * @param meal the new value for the meal
     * @return Ok if the action was successful, Bad_Request otherwise
     */
    @CrossOrigin(origins = "*")
    @PutMapping("/{dietId}/meals/{mealId}")
    public HttpStatus editMealInDiet(@PathVariable int dietId, @PathVariable int mealId, @RequestBody Meal meal) {
        System.out.println("Controller editing meal with id " + mealId);
        try{
            dietService.editMealInDiet(dietId, meal);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Deletes a meal at a given id from a given diet
     * @param dietId the id of the diet
     * @param mealId the id of th meal to be deleted
     * @return Ok if the action was successful, Bad_Request otherwise
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping ("/{dietId}/meals/{mealId}")
    public HttpStatus deleteMealFromDiet(@PathVariable int dietId, @PathVariable int mealId) {
        System.out.println("Controller deleting meal with id " + mealId);
        try{
            dietService.deleteMealFromDiet(mealId, dietId);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
