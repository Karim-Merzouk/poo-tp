public class ThresholdAlert extends Alert {
    public double getExceededValue() { 
        return ((NumericalReading)getTriggerReading()).getValue(); 
    }
    public Range getThresholdRange() { 
        return getTriggerReading().getSensor(); 
    }
}
