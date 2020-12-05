package sep3tier2.tier2.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sep3tier2.tier2.models.*;
import sep3tier2.tier2.services.training.TrainingService;

import java.util.List;

@RestController
@RequestMapping("/trainings")
public class TrainingsController
{
    @Autowired
    TrainingService trainingService;

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

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public @ResponseBody Training getTrainingById(@PathVariable int id) {
        System.out.println("Controller getting training with id " + id);
        try {
            return trainingService.getTrainingById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

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

    @CrossOrigin(origins = "*")
    @GetMapping()
    public @ResponseBody List<TrainingSVWithTime> getTrainingsInWeekForUser(@RequestParam("userId") int userId, @RequestParam("weekNumber") int weekNumber) {
        System.out.println("Controller getting trainings for user " + userId + " in week " + weekNumber);
        try {
            return trainingService.getTrainingsInWeekForUser(userId, weekNumber);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

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
