public class GPSReading extends Reading {
    private double latitude;
    private double longitude;

    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public boolean isOutOfZone(Zone z) { return false; }
}
