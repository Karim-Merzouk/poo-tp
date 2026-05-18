import java.util.Date;
public class Crop {
    private String name;
    private CropFamily cropFamily;
    private Date plantingDate;
    private Date harvestDate;
    private GrowthStatus growthStatus;
    private double optimalPHMin;
    private double optimalPHMax;
    private double optimalMoistureMin;
    private double optimalMoistureMax;

    public void setName(String name) { this.name = name; }
    public void setGrowthStatus(GrowthStatus gs) { this.growthStatus = gs; }

    public void updateGrowthStage(GrowthStatus g) { this.growthStatus = g; }
    public GrowthStatus getGrowthStatus() { return growthStatus; }
    public String getName() { return name; }
}
