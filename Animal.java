public class Animal {
    private String id;
    private String species;
    private int age;
    private double weight;
    private HealthStatus healthStatus;
    private Family family;

    public void setId(String id) { this.id = id; }
    public void setHealthStatus(HealthStatus hs) { this.healthStatus = hs; }
    public void setFamily(Family family) { this.family = family; }

    public void updateWeight(double w) { this.weight = w; }
    public void logIllness(String desc) { }
    public HealthStatus getHealthStatus() { return healthStatus; }
    public String getId() { return id; }
    public Family getFamily() { return family; }
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
}
