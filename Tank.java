public class Tank {
    private String id;
    private double capacity;
    public String getId() { return id; }
    public double getCapacity() { return capacity; }
    
    // Reflects Tank ..> AquaAnimal : "uses"
    public void accommodate(AquaAnimal animal) {
        // use AquaAnimal
    }
}
