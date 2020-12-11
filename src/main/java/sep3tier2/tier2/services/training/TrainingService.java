package sep3tier2.tier2.services.training;

import sep3tier2.tier2.models.*;

import java.util.List;

/**
 * The interface for the service class which manages training actions
 * @version 1.0
 * @author Group1
 */
public interface TrainingService
{
    /**
     * Adds a given training to a given user
     * @param training the training to be added, with the owner id
     * @return the id of the created training
     */
    int addTraining(TrainingWithOwner training) throws Exception;

    /**
     * Returns a training by its id, or null if it doesn't exist
     * @param id the id of the training
     * @return the training at the given id
     */
    TrainingWithOwner getTrainingById(int id) throws Exception;

    /**
     * Returns a list of public trainings, by skipping the first "offset" public trainings
     * @param offset the number of public trainings to be skipped
     * @return the list of trainings, without exercises and with owners
     */
    List<TrainingSVWithOwner> getPublicTrainings(int offset) throws Exception;

    /**
     * Returns a list of private trainings belonging to a given user, skipping the first "offset" private trainings
     * @param userId the id of the user
     * @param offset the number of private trainings to be skipped
     * @return the list of the user's private trainings, without exercises
     */
    List<TrainingShortVersion> getPrivateTrainingsForUser(int userId, int offset) throws Exception;

    /**
     * Returns a list of trainings for a given user (public and his own private), skipping the first "offset" private trainings
     * @param id the id of the user
     * @param offset the number of private trainings to be skipped
     * @return the list of the user's private trainings, without exercises
     */
    List<TrainingSVWithOwner> getTrainingsForUser(int id, int offset) throws Exception;

    /**
     * Returns a list with all the trainings a user has scheduled in a given week number
     * @param userId the id of the user
     * @param weekNumber the week number
     * @return the list of the user's trainings, without exercises
     */
    List<TrainingSVWithTime> getTrainingsInWeekForUser(int userId, int weekNumber) throws Exception;

    /**
     * Returns a list with all the trainings a user has scheduled in the current
     * @param userId the id of the user
     * @return the list of the user's trainings, without exercises
     */
    List<TrainingSVWithTime> getTrainingsTodayForUser(int userId) throws Exception;

    /**
     * Edits a training at a given id
     * @param training the new value for the training
     */
    void editTraining(Training training) throws Exception;

    /**
     * Deletes a training at a given id
     * @param id the id of the training to be deleted
     */
    void deleteTraining(int id) throws Exception;

    /**
     * Adds a given exercise to a given training
     * @param trainingId the id of the training
     * @param exercise the exercise to be added
     * @return the id of the created exercise
     */
    int addExerciseToTraining(int trainingId, Exercise exercise) throws Exception;

    /**
     * Edits a given exercise in a given training
     * @param trainingId the id of the training
     * @param exercise the new value for the exercise
     */
    void editExerciseInTraining(int trainingId, Exercise exercise) throws Exception;

    /**
     * Deletes an exercise at a given id from a given training
     * @param exerciseId the exercise id
     * @param trainingId the training id
     */
    void deleteExerciseFromTraining(int exerciseId, int trainingId) throws Exception;
}
