import java.util.Date;
public abstract class Reading {
    private String id;
    private Date timestamp;
    private Sensor sensor;
    private ReadingStatus readingStatus;

    public String getId() { return id; }
    public Date getTimestamp() { return timestamp; }
    public Sensor getSensor() { return sensor; }
    public boolean checkThreshold() { return false; }
    public Alert generateAlert() { return null; }
    public ReadingStatus getReadingStatus() { return readingStatus; }
}
