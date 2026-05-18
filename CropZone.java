import java.util.List;
import java.util.ArrayList;
public class CropZone extends Zone {
    private List<Crop> crops = new ArrayList<>();
    private double cropYield;

    public void addCrop(Crop c) { this.crops.add(c); }
    public List<Crop> getCrops() { return crops; }
    public String generateStatusReport() { return "Status Report..."; }
    
    @Override
    public void recordProduction() { }
}
