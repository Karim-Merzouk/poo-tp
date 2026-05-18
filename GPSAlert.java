public class GPSAlert extends Alert {
    public Animal getAnimal() { 
        return ((GPSCollar)getTriggerReading().getSensor()).getAnimal(); 
    }
    public GPSReading getLastKnownPosition() { 
        return (GPSReading)getTriggerReading(); 
    }
}
