package sep3tier2.tier2.models.training;

import java.util.List;

/**
 * A class for representing a training with exercises
 * @version 1.0
 * @author Group1
 */
public class Training
{
    private int id;
    private String title;
    private String type;
    private String timeStamp;
    private int duration;
    private boolean global;
    private boolean completed;
    private List<Exercise> exercises;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean isGlobal() {
        return global;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
}
