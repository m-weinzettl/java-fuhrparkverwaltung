package Model;

import jakarta.persistence.*;

/**
 * Die Klasse Pkw repräsentiert einen Personenkraftwagen im System.
 * Sie erbt von der abstrakten Basisklasse Vehicle und erweitert diese
 * um PKW-spezifische Attribute für die relationale Abbildung via JPA.
 */
@Entity
@Table(name = "pkw")
// Bestimmt, dass die Tabellen über den Primärschlüssel 'id' miteinander verknüpft werden.
// Diese ID entspricht der 'id' aus der übergeordneten Vehicle-Tabelle (InheritanceType.JOINED).
@PrimaryKeyJoinColumn(name = "id")
public class Pkw extends Vehicle {

    @Column(name = "number_of_doors")
    private int numberDoors;

    @Column(name = "vignette")
    private boolean vignette;

    /**
     * Konstruktor zur manuellen Erstellung eines PKWs mit allen spezifischen
     * sowie den geerbten Eigenschaften der Basisklasse.
     *
     * @param id           Eindeutige ID des Fahrzeugs
     * @param vehicleSign  Das Kennzeichen des PKWs
     * @param vehicleMiles Der aktuelle Kilometerstand
     * @param fuelAmount   Der aktuelle Tankfüllstand
     * @param tankSize     Das maximale Tankvolumen
     * @param actualDriver Der dem PKW aktuell zugewiesene Fahrer
     * @param numberDoors  Die Anzahl der Türen des PKWs
     * @param vignette     Gibt an, ob eine Autobahnvignette vorhanden ist
     */
    public Pkw(String id, String vehicleSign, double vehicleMiles, double fuelAmount,
               double tankSize, Employee actualDriver, int numberDoors, boolean vignette) {

        // Aufruf des Konstruktors der abstrakten Oberklasse Vehicle
        super(id, vehicleSign, vehicleMiles, fuelAmount, tankSize, actualDriver);

        this.numberDoors = numberDoors;
        this.vignette = vignette;
    }

    /**
     * Der Standardkonstruktor (No-Arg-Constructor).
     * Wird zwingend von JPA benötigt, um Objekte beim Auslesen aus der Datenbank zu instanziieren.
     */
    public Pkw() {
    }

    // ==========================================
    // Getter und Setter (Zugriffsmethoden)
    // ==========================================

    public boolean isVignette() {
        return vignette;
    }

    public void setVignette(boolean vignette) {
        this.vignette = vignette;
    }

    public int getNumberDoors() {
        return numberDoors;
    }

    public void setNumberDoors(int numberDoors) {
        this.numberDoors = numberDoors;
    }
}