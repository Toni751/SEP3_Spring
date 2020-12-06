package sep3tier2.tier2.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sep3tier2.tier2.models.*;
import sep3tier2.tier2.services.diet.DietService;

import java.util.List;

@RestController
@RequestMapping("/diets")
public class DietsController
{
    @Autowired
    DietService dietService;

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
