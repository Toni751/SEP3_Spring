package sep3tier2.tier2.models.training;

/**
 * A class for representing an exercise with the training it belongs to
 * @version 1.0
 * @author Group1
 */
public class ExerciseWithTraining extends Exercise
{
    private int trainingId;

    public ExerciseWithTraining(int id, String title, String description, int trainingId) {
        super(id, title, description);
        this.trainingId = trainingId;
    }

    public int getTrainingId() {
        return trainingId;
    }
}
