public abstract class Sensor implements Range {
    private String code;
    private StatusSensor status;
    private SType sensorType;
    private Zone zone;
    private Range thresholdRange;

    public void activate() { this.status = StatusSensor.ACTIVE; }
    public void suspend() { this.status = StatusSensor.SUSPENDED; }
    public void setFaulty() { this.status = StatusSensor.FAULTY; }
    public void setCode(String code) { this.code = code; }
    public String getCode() { return code; }
    public StatusSensor getStatus() { return status; }
    
    public abstract Reading sendReading();
    
    @Override public double getMin() { return thresholdRange.getMin(); }
    @Override public double getMax() { return thresholdRange.getMax(); }
    @Override public boolean isInRange(double value) { return thresholdRange.isInRange(value); }
}
