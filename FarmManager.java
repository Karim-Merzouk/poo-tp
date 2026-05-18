import java.util.ArrayList;
import java.util.List;

/**
 * Main class acting as the "Gestionnaire de la ferme" (Farm Manager).
 * Connects Zones, Sensors, Entities and utilizes the Report class.
 */
public class FarmManager {
    
    // Centralized lists acting as our system database
    private static List<Zone> farmZones = new ArrayList<>();
    private static List<Alert> activeAlerts = new ArrayList<>();
    private static List<Alert> alertHistory = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== INITIALISATION DU SYSTÈME SMART FARMING ===\n");

        // ---------------------------------------------------------
        // 1. Gérer les zones et les entités
        // ---------------------------------------------------------
        // --- Crop Zone ---
        CropZone zoneBlé = new CropZone();
        zoneBlé.setName("Zone de Blé Alpha");
        zoneBlé.setCode("Z-BLE-01");
        
        Crop ble = new Crop(); 
        ble.setName("Blé d'hiver");
        ble.setGrowthStatus(GrowthStatus.GROWTH);
        zoneBlé.addCrop(ble);

        // --- Ruminant Zone ---
        RuminantZone zoneVaches = new RuminantZone();
        zoneVaches.setName("Pâturage Vaches Laitières");
        zoneVaches.setCode("Z-VACH-01");
        
        Animal vache = new Animal(); 
        vache.setId("V-001");
        vache.setSpecies("Vache Laitière");
        vache.setHealthStatus(HealthStatus.HEALTHY);
        vache.setFamily(Family.RUMINANT);
        zoneVaches.addAnimal(vache);
        
        System.out.println("Test ajout mauvaise famille dans RuminantZone :");
        Animal pouletTest = new Animal();
        pouletTest.setFamily(Family.POULTRY);
        zoneVaches.addAnimal(pouletTest); // Should print error

        // --- Poultry Zone ---
        PoultryZone zonePoulets = new PoultryZone();
        zonePoulets.setName("Poulailler Principal");
        zonePoulets.setCode("Z-POUL-01");

        Animal poulet = new Animal();
        poulet.setId("P-001");
        poulet.setSpecies("Poule Pondeuse");
        poulet.setHealthStatus(HealthStatus.HEALTHY);
        poulet.setFamily(Family.POULTRY);
        zonePoulets.addAnimal(poulet);

        System.out.println("Test ajout mauvaise famille dans PoultryZone :");
        Animal vacheTest = new Animal();
        vacheTest.setFamily(Family.RUMINANT);
        zonePoulets.addAnimal(vacheTest); // Should print error

        // --- Aquaculture Zone ---
        AquacultureZone zonePoissons = new AquacultureZone();
        zonePoissons.setName("Bassin d'Aquaculture Principal");
        zonePoissons.setCode("Z-AQUA-01");
        
        Tank tank = new Tank(); // Tank is decoupled but we instantiate to test features
        AquaAnimal poisson = new AquaAnimal();
        poisson.setId("F-001");
        poisson.setSpecies("Saumon");
        poisson.setFamily(Family.RUMINANT); // AquaAnimal might not restrict family, just stub testing
        tank.accommodate(poisson);

        farmZones.add(zoneBlé);
        farmZones.add(zoneVaches);
        farmZones.add(zonePoulets);
        farmZones.add(zonePoissons);

        // ---------------------------------------------------------
        // 2. Gérer les Capteurs
        // ---------------------------------------------------------
        SoilSensor capteurHumidite = new SoilSensor();
        capteurHumidite.setCode("S-SOIL-12");
        capteurHumidite.activate();
        zoneBlé.addSensor(capteurHumidite);
        
        EnvironmentSensor capteurEnv = new EnvironmentSensor();
        capteurEnv.setCode("S-ENV-01");
        capteurEnv.activate();
        zoneBlé.addSensor(capteurEnv);

        GPSCollar collierVache = new GPSCollar();
        collierVache.setCode("S-GPS-V001");
        collierVache.activate();
        zoneVaches.addSensor(collierVache);

        BiometricSensor bioPoulet = new BiometricSensor();
        bioPoulet.setCode("S-BIO-P001");
        bioPoulet.activate();
        zonePoulets.addSensor(bioPoulet);

        WaterSensor capteurEau = new WaterSensor();
        capteurEau.setCode("S-WAT-01");
        capteurEau.activate();
        zonePoissons.addSensor(capteurEau);

        // ---------------------------------------------------------
        // 3. Tester d'autres fonctionnalités (Feeding & Production)
        // ---------------------------------------------------------
        System.out.println("\n--- TEST DES PROGRAMMES DE NOURRISSAGE ---");
        System.out.println("Vaches: " + zoneVaches.getFeedType() + ", " + zoneVaches.getQuantityPerMeal() + "kg, " + zoneVaches.getSchedule());
        System.out.println("Poulets: " + zonePoulets.getFeedType() + ", " + zonePoulets.getQuantityPerMeal() + "kg, " + zonePoulets.getSchedule());
        System.out.println("Poissons: " + zonePoissons.getFeedType() + ", " + zonePoissons.getQuantityPerMeal() + "kg, " + zonePoissons.getSchedule());

        System.out.println("\n--- TEST RECORD PRODUCTION ---");
        zoneBlé.recordProduction();
        zoneVaches.recordProduction();
        zonePoulets.recordProduction();
        zonePoissons.recordProduction();
        System.out.println("Productions enregistrées (si implémenté).");

        // ---------------------------------------------------------
        // 4. Simuler les Alertes (Threshold & GPS)
        // ---------------------------------------------------------
        ThresholdAlert alerteUrgent = new ThresholdAlert();
        alerteUrgent.setSeverity(SeverityLevel.CRITICAL);
        
        GPSAlert alerteFugue = new GPSAlert();
        alerteFugue.setSeverity(SeverityLevel.WARNING);
        
        activeAlerts.add(alerteUrgent);
        activeAlerts.add(alerteFugue);
        alertHistory.add(alerteUrgent);
        alertHistory.add(alerteFugue);

        alerteUrgent.acknowledge();

        // ---------------------------------------------------------
        // 5. Génération des Rapports via la classe Report
        // ---------------------------------------------------------
        System.out.println("\nGénération des rapports demandés par le TP...\n");
        
        System.out.println(Report.generateZoneOverview(farmZones));
        System.out.println(Report.generateCropStatusReport(zoneBlé));
        System.out.println(Report.generateZoneProductionAndSensorReport(zoneVaches));
        System.out.println(Report.generateAlertHistoryReport(alertHistory));
        
        System.out.println("=== FIN DES TESTS ===");
    }
}