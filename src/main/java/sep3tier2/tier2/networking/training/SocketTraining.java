package sep3tier2.tier2.networking.training;

import sep3tier2.tier2.models.*;

import java.util.List;

public interface SocketTraining
{
    int addTraining(TrainingWithOwner training) throws Exception;
    TrainingWithOwner getTrainingById(int id) throws Exception;
    List<TrainingSVWithOwner> getPublicTrainings(int offset) throws Exception;
    List<TrainingShortVersion> getPrivateTrainingsForUser(int userId, int offset) throws Exception;
    List<TrainingSVWithOwner> getTrainingsForUser(int id, int offset) throws Exception;
    List<TrainingSVWithTime> getTrainingsInWeekForUser(int userId, int weekNumber) throws Exception;
    void editTraining(Training training) throws Exception;
    void deleteTraining(int id) throws Exception;
    int addExerciseToTraining(ExerciseWithTraining exercise) throws Exception;
    void editExerciseInTraining(ExerciseWithTraining exercise) throws Exception;
    void deleteExerciseFromTraining(int exerciseId, int trainingId) throws Exception;
}
