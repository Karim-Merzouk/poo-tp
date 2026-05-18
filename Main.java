import java.util.ArrayList;
import java.util.List;

/**
 * Main class acting as the "Gestionnaire de la ferme" (Farm Manager).
 * Connects Zones, Sensors, Entities and utilizes the Report class.
 */
public class Main {
    
    // Centralized lists acting as our system database
    private static List<Zone> farmZones = new ArrayList<>();
    private static List<Alert> activeAlerts = new ArrayList<>();
    private static List<Alert> alertHistory = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== INITIALISATION DU SYSTÈME SMART FARMING ===\n");

        // ---------------------------------------------------------
        // 1. Gérer les zones et les entités
        // ---------------------------------------------------------
        CropZone zoneBlé = new CropZone();
        zoneBlé.setName("Zone de Blé Alpha");
        zoneBlé.setCode("Z-BLE-01");
        
        RuminantZone zoneVaches = new RuminantZone();
        zoneVaches.setName("Pâturage Vaches Laitières");
        zoneVaches.setCode("Z-VACH-01");
        
        AquacultureZone zonePoissons = new AquacultureZone();
        zonePoissons.setName("Bassin d'Aquaculture Principal");
        zonePoissons.setCode("Z-AQUA-01");

        farmZones.add(zoneBlé);
        farmZones.add(zoneVaches);
        farmZones.add(zonePoissons);

        // Ajouter des entités (Cultures et Animaux)
        Crop ble = new Crop(); 
        ble.setName("Blé d'hiver");
        ble.setGrowthStatus(GrowthStatus.GROWTH);
        zoneBlé.addCrop(ble);

        Animal vache1 = new Animal(); 
        vache1.setId("V-001");
        vache1.setHealthStatus(HealthStatus.HEALTHY);
        vache1.setFamily(Family.RUMINANT);
        zoneVaches.addAnimal(vache1);

        // ---------------------------------------------------------
        // 2. Gérer les Capteurs
        // ---------------------------------------------------------
        SoilSensor capteurHumidite = new SoilSensor();
        capteurHumidite.setCode("S-SOIL-12");
        capteurHumidite.activate();
        zoneBlé.addSensor(capteurHumidite);

        GPSCollar collierVache = new GPSCollar();
        collierVache.setCode("S-GPS-V001");
        collierVache.activate();
        zoneVaches.addSensor(collierVache);

        // ---------------------------------------------------------
        // 3. Simuler une Alerte (Gérer le système d'alertes)
        // ---------------------------------------------------------
        // As checkThreshold and generateAlert are currently stubs returning null,
        // we create a manual dummy alert to simulate the system behavior.
        ThresholdAlert alerteUrgent = new ThresholdAlert();
        alerteUrgent.setSeverity(SeverityLevel.CRITICAL);
        
        activeAlerts.add(alerteUrgent);
        alertHistory.add(alerteUrgent);

        // ---------------------------------------------------------
        // 4. Génération des Rapports via la classe Report
        // ---------------------------------------------------------
        System.out.println("Génération des rapports demandés par le TP...\n");
        
        // Report 1 : Vue d'ensemble (Requirement 1)
        System.out.println(Report.generateZoneOverview(farmZones));

        // Report 2 : État des cultures (Requirement 2)
        System.out.println(Report.generateCropStatusReport(zoneBlé));

        // Report 3 : Tableau de bord et Production pour une zone (Requirement 4 & 1)
        System.out.println(Report.generateZoneProductionAndSensorReport(zoneVaches));

        // Report 4 : Historique des alertes (Requirement 5)
        System.out.println(Report.generateAlertHistoryReport(alertHistory));
        
        System.out.println("=== FIN DES TESTS ===");
    }
}