import java.util.ArrayList;
import java.util.List;

public class PoultryZone extends LivestockZone {
    private int eggCount;
    private List<Animal> animals = new ArrayList();
    public int getEggCount() { return eggCount; }
    @Override public void recordProduction() { }
    @Override public String getFeedType() { return "Poultry Feed"; }
    @Override public double getQuantityPerMeal() { return 0.5; }
    @Override public String getSchedule() { return "Morning"; }
    
    @Override
    public void addAnimal(Animal animal) {
        if (animal.getFamily() == Family.POULTRY) {
            super.getAnimals().add(animal);
        } else {
            System.out.println("Error: Only poultry can be added to the PoultryZone.");
        }
    }
}
