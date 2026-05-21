package Model;

/**
 * Die VehicleFactory implementiert das Factory-Entwurfsmuster (Fabrikmuster).
 * Sie kapselt die Instanziierung der konkreten Fahrzeug-Unterklassen (Pkw, Lkw, Motorcycle)
 * und entkoppelt den restlichen Code von den konkreten Konstruktoren.
 */
public class VehicleFactory {

    /**
     * Erstellt eine neue Instanz eines PKWs.
     *
     * @param id Eindeutige ID des Fahrzeugs
     * @param vehicleSign Kennzeichen
     * @param vehicleMiles Kilometerstand
     * @param fuelAmount Aktueller Füllstand
     * @param tankSize Maximale Tankgröße
     * @param actualDriver Der zugewiesene Fahrer (Mitarbeiter)
     * @param numberOfDoors Anzahl der Türen des PKWs
     * @param vignette Gibt an, ob eine Autobahnvignette vorhanden ist
     * @return Ein vollständig initialisiertes Pkw-Objekt
     */
    public Pkw createPkw(String id, String vehicleSign, double vehicleMiles,
                         double fuelAmount, double tankSize, Employee actualDriver,
                         int numberOfDoors, boolean vignette) {
        return new Pkw(id, vehicleSign, vehicleMiles, fuelAmount, tankSize,
                actualDriver, numberOfDoors, vignette);
    }

    /**
     * Erstellt eine neue Instanz eines LKWs.
     *
     * @param id Eindeutige ID des Fahrzeugs
     * @param vehicleSign Kennzeichen
     * @param vehicleMiles Kilometerstand
     * @param fuelAmount Aktueller Füllstand
     * @param tankSize Maximale Tankgröße
     * @param actualDriver Der zugewiesene Fahrer (Mitarbeiter)
     * @param maxWeightCapacity Maximal zulässiges Gesamtgewicht in Tonnen
     * @param loadCapacity Maximale Zuladung in Tonnen
     * @return Ein vollständig initialisiertes Lkw-Objekt
     */
    public Lkw createLkw(String id, String vehicleSign, double vehicleMiles,
                         double fuelAmount, double tankSize, Employee actualDriver,
                         double maxWeightCapacity, double loadCapacity) {
        return new Lkw(id, vehicleSign, vehicleMiles, fuelAmount, tankSize,
                actualDriver, maxWeightCapacity, loadCapacity);
    }

    /**
     * Erstellt eine neue Instanz eines Motorrads.
     *
     * @param id Eindeutige ID des Fahrzeugs
     * @param vehicleSign Kennzeichen
     * @param vehicleMiles Kilometerstand
     * @param fuelAmount Aktueller Füllstand
     * @param tankSize Maximale Tankgröße
     * @param actualDriver Der zugewiesene Fahrer (Mitarbeiter)
     * @param helmetCase Gibt an, ob ein Helmkoffer montiert ist
     * @param license Die benötigte Führerscheinklasse (z.B. "A")
     * @return Ein vollständig initialisiertes Motorcycle-Objekt
     */
    public Motorcycle createMotorcycle(String id, String vehicleSign, double vehicleMiles,
                                       double fuelAmount, double tankSize, Employee actualDriver,
                                       boolean helmetCase, String license) {
        return new Motorcycle(id, vehicleSign, vehicleMiles, fuelAmount, tankSize,
                actualDriver, helmetCase, license);
    }
}