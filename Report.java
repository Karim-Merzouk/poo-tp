import java.util.List;

public class Report {

    /**
     * "Afficher une vue d'ensemble de toutes les zones avec leur statut et le nombre d'entités hébergées."
     * Requisité : 1. Gérer les zones (Vue d'ensemble)
     */
    public static String generateZoneOverview(List<Zone> systemZones) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- VUE D'ENSEMBLE DES ZONES ---\n");
        for (Zone z : systemZones) {
            String zName = z.getName() != null ? z.getName() : z.getClass().getSimpleName();
            sb.append("Zone Name: ").append(zName) // Using class name if name is null
              .append(" | Statut: ").append(z.getSensors().isEmpty() ? "INCONNU" : "VERIFIER STATUT")
              .append(" | Nombre Capteurs: ").append(z.getSensors().size());
            
            if (z instanceof CropZone) {
                sb.append(" | Entités (Cultures): ").append(((CropZone) z).getCrops().size());
            } else if (z instanceof LivestockZone) {
                sb.append(" | Entités (Animaux): ").append(((LivestockZone) z).getAnimals().size());
            } else if (z instanceof AquacultureZone) {
                sb.append(" | Entités (Aquatiques): 1 Bassin");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * "Générer un rapport de l'état des cultures par zone."
     * Requisité : 2. Gérer les cultures
     */
    public static String generateCropStatusReport(CropZone cropZone) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- RAPPORT DES CULTURES ---\n");
        List<Crop> crops = cropZone.getCrops();
        if (crops.isEmpty()) {
            sb.append("Aucune culture dans cette zone.\n");
        } else {
            for (Crop c : crops) {
                sb.append("Culture: ").append(c.getName() != null ? c.getName() : "Inconnu")
                  .append(" | Stade: ").append(c.getGrowthStatus())
                  .append("\n");
            }
        }
        // Utilisation de la méthode existante (qui est actuellement un stub)
        sb.append("Notes de la zone: ").append(cropZone.generateStatusReport()).append("\n");
        return sb.toString();
    }

    /**
     * "Afficher un tableau de bord des relevés par zone" & "Historique de production"
     * Requisité : 4. Gérer les capteurs & Production
     */
    public static String generateZoneProductionAndSensorReport(Zone zone) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- TABLEAU DE BORD ZONE ---\n");
        sb.append("Capteurs associés: ").append(zone.getSensors().size()).append("\n");
        for (Sensor s : zone.getSensors()) {
            sb.append("  - Capteur Code: ").append(s.getCode())
              .append(" | Type: ").append(s.getClass().getSimpleName())
              .append(" | Statut: ").append(s.getStatus())
              .append("\n");
        }
        
        sb.append("Statut Production: \n");
        if (zone instanceof RuminantZone) {
            sb.append("  - Rendement Laitier: ").append(((RuminantZone) zone).getMilkYield()).append("\n");
        } else if (zone instanceof PoultryZone) {
            sb.append("  - Production d'oeufs: ").append(((PoultryZone) zone).getEggCount()).append("\n");
        }
        
        return sb.toString();
    }

    /**
     * "Consulter l'historique des alertes..."
     * Requisité : 5. Gérer le système d'alertes
     */
    public static String generateAlertHistoryReport(List<Alert> alertHistory) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- HISTORIQUE DES ALERTES ---\n");
        if (alertHistory == null || alertHistory.isEmpty()) {
            sb.append("Aucune alerte enregistrée.\n");
        } else {
            for (Alert a : alertHistory) {
                sb.append("Alerte: ").append(a.getClass().getSimpleName())
                  .append(" | Gravité: ").append(a.getSeverity())
                  .append(" | Acquittée: ").append(a.isAcknowledged() ? "OUI" : "NON")
                  .append("\n");
            }
        }
        return sb.toString();
    }
}
