package sep3tier2.tier2.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sep3tier2.tier2.models.*;
import sep3tier2.tier2.services.training.TrainingService;
import java.util.List;

/**
 * Rest API Controller for training-related requests at the endpoint "/trainings"
 * @version 1.0
 * @author Group1
 */
@RestController
@RequestMapping("/trainings")
public class TrainingsController
{
    @Autowired
    TrainingService trainingService;

    /**
     * Adds a given training to a given user
     * @param training the training to be added, with the owner id
     * @return the id of the created training
     */
    @CrossOrigin(origins = "*")
    @PostMapping
    public @ResponseBody int addTraining(@RequestBody TrainingWithOwner training) {
        System.out.println("Controller adding training");
        try {
            return trainingService.addTraining(training);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Returns a training by its id, or null if it doesn't exist
     * @param id the id of the training
     * @return the training at the given id
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public @ResponseBody TrainingWithOwner getTrainingById(@PathVariable int id) {
        System.out.println("Controller getting training with id " + id);
        try {
            return trainingService.getTrainingById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Returns a list of public trainings, by skipping the first "offset" public trainings
     * @param offset the number of public trainings to be skipped
     * @return the list of trainings, without exercises and with owners
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/public")
    public @ResponseBody List<TrainingSVWithOwner> getPublicTrainings(@RequestParam("offset") int offset) {
        System.out.println("Controller getting public trainings with offset " + offset);
        try {
            return trainingService.getPublicTrainings(offset);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Returns a list of private trainings belonging to a given user, skipping the first "offset" private trainings
     * @param userId the id of the user
     * @param offset the number of private trainings to be skipped
     * @return the list of the user's private trainings, without exercises
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/private")
    public @ResponseBody List<TrainingShortVersion> getPrivateTrainingsForUser(@RequestParam("userId") int userId, @RequestParam("offset") int offset) {
        System.out.println("Controller getting private trainings for user " + userId + " with offset " + offset);
        try {
            return trainingService.getPrivateTrainingsForUser(userId, offset);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Returns a list of trainings for a given user (public and his own private), skipping the first "offset" private trainings
     * @param id the id of the user
     * @param offset the number of private trainings to be skipped
     * @return the list of the user's private trainings, without exercises
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/users/{id}")
    public @ResponseBody List<TrainingSVWithOwner> getTrainingsForUser(@PathVariable int id, @RequestParam("offset") int offset) {
        System.out.println("Controller getting trainings for user " + id + " with offset " + offset);
        try {
            return trainingService.getTrainingsForUser(id, offset);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Returns a list with all the trainings a user has scheduled in a given week number
     * @param userId the id of the user
     * @param weekNumber the week number
     * @return the list of the user's trainings, without exercises
     */
    @CrossOrigin(origins = "*")
    @GetMapping()
    public @ResponseBody List<TrainingSVWithTime> getTrainingsInWeekForUser(@RequestParam("userId") int userId, @RequestParam("weekNumber") int weekNumber){
        System.out.println("Controller getting trainings for user " + userId + " in week " + weekNumber);
        try {
            return trainingService.getTrainingsInWeekForUser(userId, weekNumber);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Returns a list with all the trainings a user has scheduled in the current
     * @param userId the id of the user
     * @return the list of the user's trainings, without exercises
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/today")
    public @ResponseBody List<TrainingSVWithTime> getTrainingsTodayForUser(@RequestParam("userId") int userId){
        System.out.println("Controller getting trainings today for user " + userId);
        try {
            return trainingService.getTrainingsTodayForUser(userId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Edits a training at a given id
     * @param id the id of the training to be edited
     * @param training the new value for the training
     * @return Ok if the action was successful, Bad_Request otherwise
     */
    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public HttpStatus editTraining(@PathVariable int id, @RequestBody Training training) {
        System.out.println("Controller editing training with id " + id);
        try {
            trainingService.editTraining(training);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Deletes a training at a given id
     * @param id the id of the training to be deleted
     * @return Ok if the action was successful, Bad_Request otherwise
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public HttpStatus deleteTraining(@PathVariable int id) {
        System.out.println("Controller deleting training with id " + id);
        try {
            trainingService.deleteTraining(id);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Adds a given exercise to a given training
     * @param id the id of the training
     * @param exercise the exercise to be added
     * @return the id of the created exercise
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/{id}/exercises")
    public @ResponseBody int addExerciseToTraining(@PathVariable int id, @RequestBody Exercise exercise) {
        System.out.println("Controller adding exercise to training " + id);
        try {
            return trainingService.addExerciseToTraining(id, exercise);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Edits a given exercise in a given training
     * @param exerciseId the id of the exercise
     * @param trainingId the id of the training
     * @param exercise the new value for the exercise
     * @return Ok if the action was successful, Bad_Request otherwise
     */
    @CrossOrigin(origins = "*")
    @PutMapping("{trainingId}/exercises/{exerciseId}")
    public HttpStatus editExerciseInTraining(@PathVariable int exerciseId, @PathVariable int trainingId, @RequestBody Exercise exercise){
        System.out.println("Controller editing exercise with id " + exerciseId);
        try {
            trainingService.editExerciseInTraining(trainingId, exercise);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Deletes an exercise at a given id from a given training
     * @param exerciseId the exercise id
     * @param trainingId the training id
     * @return Ok if the action was successful, Bad_Request otherwise
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping ("{trainingId}/exercises/{exerciseId}")
    public HttpStatus deleteExerciseFromTraining(@PathVariable int exerciseId, @PathVariable int trainingId){
        System.out.println("Controller deleting exercise with id " + exerciseId);
        try {
            trainingService.deleteExerciseFromTraining(exerciseId, trainingId);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
