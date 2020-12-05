package sep3tier2.tier2.models;

import java.util.List;

public class Training
{
    private int id;
    private String title;
    private String type;
    private String timeStamp;
    private int duration;
    private boolean isCompleted;
    private boolean global;
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
        return isCompleted;
    }

    public boolean isPublic() {
        return global;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
}
