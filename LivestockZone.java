import java.util.List;
import java.util.ArrayList;
public abstract class LivestockZone extends Zone implements FeedingProgram {
    private List<Animal> animals = new ArrayList<>();

    public void addAnimal(Animal a) { this.animals.add(a); }
    public List<Animal> getAnimals() { return animals; }
    public FeedingProgram getFeedingProgram() { return this; }
}
