package sep3tier2.tier2.services.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sep3tier2.tier2.models.*;
import sep3tier2.tier2.networking.training.SocketTraining;

import java.util.List;

/**
 * The service class for handling training requests
 * @version 1.0
 * @author Group1
 */
@Service
public class TrainingServiceImpl implements TrainingService
{
    @Autowired
    SocketTraining socketTraining;

    @Override
    public int addTraining(TrainingWithOwner training) throws Exception {
        if(training.getTitle() == null || training.getTitle().equals(""))
            throw new Exception("Training title cannot be empty");
        if(training.getType() == null || training.getType().equals(""))
            throw new Exception("Training type cannot be empty");

        try {
            return socketTraining.addTraining(training);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public TrainingWithOwner getTrainingById(int id) throws Exception {
        if(id <= 0)
            throw new Exception("Invalid training id");
        try{
            return socketTraining.getTrainingById(id);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<TrainingSVWithOwner> getPublicTrainings(int offset) throws Exception {
        if(offset < 0)
            throw new Exception("Invalid trainings offset");
        try{
            return socketTraining.getPublicTrainings(offset);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<TrainingShortVersion> getPrivateTrainingsForUser(int userId, int offset) throws Exception {
        if (userId <= 0 && offset < 0)
            throw new Exception("Invalid parameters for get private trainings");
        try{
            return socketTraining.getPrivateTrainingsForUser(userId, offset);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<TrainingSVWithOwner> getTrainingsForUser(int id, int offset) throws Exception {
        if (id <= 0 && offset < 0)
            throw new Exception("Invalid parameters for get trainings for user");
        try{
            return socketTraining.getTrainingsForUser(id, offset);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<TrainingSVWithTime> getTrainingsInWeekForUser(int userId, int weekNumber) throws Exception {
        if (userId <= 0 && weekNumber < 0)
            throw new Exception("Invalid parameters for get trainings in week");
        try{
            return socketTraining.getTrainingsInWeekForUser(userId, weekNumber);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<TrainingSVWithTime> getTrainingsTodayForUser(int userId) throws Exception {
        if (userId <= 0)
            throw new Exception("Invalid user id for get trainings today "+ userId);
        try{
            return socketTraining.getTrainingsTodayForUser(userId);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void editTraining(Training training) throws Exception {
        try{
            socketTraining.editTraining(training);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteTraining(int id) throws Exception {
        if(id <= 0)
            throw new Exception("Invalid training id");
        try{
            socketTraining.deleteTraining(id);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public int addExerciseToTraining(int trainingId, Exercise exercise) throws Exception {
        if(trainingId <= 0)
            throw new Exception("Invalid training id");
        try{
            return socketTraining.addExerciseToTraining(
                    new ExerciseWithTraining(exercise.getId(), exercise.getTitle(), exercise.getDescription(), trainingId));
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void editExerciseInTraining(int trainingId, Exercise exercise) throws Exception {
        if(trainingId <= 0)
            throw new Exception("Invalid training id");
        try{
            socketTraining.editExerciseInTraining(new ExerciseWithTraining(exercise.getId(), exercise.getTitle(), exercise.getDescription(), trainingId));
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteExerciseFromTraining(int exerciseId, int trainingId) throws Exception {
        if(exerciseId <= 0 || trainingId <= 0)
            throw new Exception("Invalid exercise/training id");
        try{
            socketTraining.deleteExerciseFromTraining(exerciseId, trainingId);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
