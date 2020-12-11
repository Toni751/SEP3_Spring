package sep3tier2.tier2.models;

/**
 * A class for representing a training short version with time, date and duration
 * @version 1.0
 * @author Group1
 */
public class TrainingSVWithTime extends TrainingShortVersion
{
    private String timeStamp;
    private int duration;

    public String getTimeStamp() {
        return timeStamp;
    }

    public int getDuration() {
        return duration;
    }
}
