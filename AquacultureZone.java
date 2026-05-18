public class AquacultureZone extends Zone implements FeedingProgram {
    private Tank tank;
    private int animalCount;
    private String species;
    private double harvestWeight;

    public Tank getTank() { return tank; }
    @Override public void recordProduction() { }
    @Override public String getFeedType() { return "Aqua Feed"; }
    @Override public double getQuantityPerMeal() { return 2.0; }
    @Override public String getSchedule() { return "Afternoon"; }
}
