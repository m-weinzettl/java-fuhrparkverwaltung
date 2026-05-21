package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

/**
 * Die Klasse Motorcycle repräsentiert ein Motorrad im System.
 * Sie erbt von der abstrakten Basisklasse Vehicle und erweitert diese
 * um motorradspezifische Attribute für die relationale Abbildung via JPA.
 */
@Entity
@Table(name = "motorcycle")
// Bestimmt, dass die Tabellen über den Primärschlüssel 'id' miteinander verknüpft werden.
// Diese ID entspricht der 'id' aus der übergeordneten Vehicle-Tabelle (InheritanceType.JOINED).
@PrimaryKeyJoinColumn(name = "id")
public class Motorcycle extends Vehicle {

    @Column(name = "helmet_case")
    private boolean helmetCase;

    @Column(name = "license")
    private String license;

    /**
     * Konstruktor zur manuellen Erstellung eines Motorrads mit allen spezifischen
     * sowie den geerbten Eigenschaften der Basisklasse.
     *
     * @param id           Eindeutige ID des Fahrzeugs
     * @param vehicleSign  Das Kennzeichen des Motorrads
     * @param vehicleMiles Der aktuelle Kilometerstand
     * @param fuelAmount   Der aktuelle Tankfüllstand
     * @param tankSize     Das maximale Tankvolumen
     * @param actualDriver Der dem Motorrad aktuell zugewiesene Fahrer
     * @param helmetCase   Gibt an, ob ein Helmkoffer vorhanden ist
     * @param license      Die für dieses Motorrad benötigte Führerscheinklasse (z.B. "A")
     */
    public Motorcycle(String id, String vehicleSign, double vehicleMiles, double fuelAmount,
                      double tankSize, Employee actualDriver, boolean helmetCase, String license) {

        // Aufruf des Konstruktors der abstrakten Oberklasse Vehicle
        super(id, vehicleSign, vehicleMiles, fuelAmount, tankSize, actualDriver);

        this.helmetCase = helmetCase;
        this.license = license;
    }

    /**
     * Der Standardkonstruktor (No-Arg-Constructor).
     * Wird zwingend von JPA benötigt, um Objekte beim Auslesen aus der Datenbank zu instanziieren.
     */
    public Motorcycle() {
    }

    // ==========================================
    // Getter und Setter (Zugriffsmethoden)
    // ==========================================

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public boolean isHelmetCase() {
        return helmetCase;
    }

    public void setHelmetCase(boolean helmetCase) {
        this.helmetCase = helmetCase;
    }
}