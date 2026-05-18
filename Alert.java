import java.util.Date;
public abstract class Alert {
    private String id;
    private Date triggeredAt;
    private SeverityLevel severity;
    private Reading triggerReading;
    private boolean acknowledged;

    public void setSeverity(SeverityLevel severity) { this.severity = severity; }
    
    public void acknowledge() { this.acknowledged = true; }
    public void dismiss() { this.acknowledged = true; } 
    public SeverityLevel getSeverity() { return severity; }
    public Reading getTriggerReading() { return triggerReading; }
    public boolean isAcknowledged() { return acknowledged; }
}
