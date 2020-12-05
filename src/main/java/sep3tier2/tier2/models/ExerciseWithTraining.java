package sep3tier2.tier2.models;

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
