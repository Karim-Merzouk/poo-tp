public class RuminantZone extends LivestockZone {
    private double milkYield;
    public double getMilkYield() { return milkYield; }
    @Override public void recordProduction() { }
    @Override public String getFeedType() { return "Ruminant Feed"; }
    @Override public double getQuantityPerMeal() { return 5.0; }
    @Override public String getSchedule() { return "Morning/Evening"; }
    
    @Override
    public void addAnimal(Animal animal) {
        if (animal.getFamily() == Family.RUMINANT) {
            super.getAnimals().add(animal);
        } else {
            System.out.println("Error: Only ruminants can be added to the RuminantZone.");
        }
    }
}
