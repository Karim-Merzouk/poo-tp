import java.util.List;
import java.util.ArrayList;
public abstract class Zone {
    private String code;
    private String name;
    private StatusZone status = StatusZone.ACTIVE;
    private List<Sensor> sensors = new ArrayList<>();

    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public String getCode() { return code; }

    public void suspend() { this.status = StatusZone.SUSPENDED; }
    public void reactivate() { this.status = StatusZone.ACTIVE; }
    public void addSensor(Sensor s) { this.sensors.add(s); }
    public List<Sensor> getSensors() { return sensors; }
    public abstract void recordProduction();
}
