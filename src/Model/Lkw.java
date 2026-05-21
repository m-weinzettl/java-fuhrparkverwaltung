package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

/**
 * Die Klasse Lkw repräsentiert einen Lastkraftwagen im System.
 * Sie erbt von der abstrakten Basisklasse Vehicle und erweitert diese
 * um LKW-spezifische Attribute für die relationale Abbildung via JPA.
 */
@Entity
@Table(name = "lkw")
// Bestimmt, dass die Tabellen über den Primärschlüssel 'id' miteinander verknüpft werden.
// Diese ID entspricht der 'id' aus der übergeordneten Vehicle-Tabelle (InheritanceType.JOINED).
@PrimaryKeyJoinColumn(name = "id")
public class Lkw extends Vehicle {

    @Column(name = "max_weight_capacity")
    private double maxWeightCapacity;

    @Column(name = "load_capacity")
    private double loadCapacity;

    /**
     * Konstruktor zur manuellen Erstellung eines LKWs mit allen spezifischen
     * sowie den geerbten Eigenschaften der Basisklasse.
     *
     * @param id                Eindeutige ID des Fahrzeugs
     * @param vehicleSign       Das Kennzeichen des LKWs
     * @param vehicleMiles      Der aktuelle Kilometerstand
     * @param fuelAmount        Der aktuelle Tankfüllstand
     * @param tankSize          Das maximale Tankvolumen
     * @param actualDriver      Der dem LKW aktuell zugewiesene Fahrer
     * @param maxWeightCapacity Das maximal erlaubte Gesamtgewicht in Tonnen
     * @param loadCapacity      Die maximale Zuladung in Tonnen
     */
    public Lkw(String id, String vehicleSign, double vehicleMiles, double fuelAmount,
               double tankSize, Employee actualDriver, double maxWeightCapacity, double loadCapacity) {

        // Aufruf des Konstruktors der abstrakten Oberklasse Vehicle
        super(id, vehicleSign, vehicleMiles, fuelAmount, tankSize, actualDriver);

        this.maxWeightCapacity = maxWeightCapacity;
        this.loadCapacity = loadCapacity;
    }

    /**
     * Der Standardkonstruktor (No-Arg-Constructor).
     * Wird zwingend von JPA benötigt, um Objekte beim Auslesen aus der Datenbank zu instanziieren.
     */
    public Lkw() {
    }

    // ==========================================
    // Getter und Setter (Zugriffsmethoden)
    // ==========================================

    public double getMaxWeightCapacity() {
        return maxWeightCapacity;
    }

    public void setMaxWeightCapacity(double maxWeightCapacity) {
        this.maxWeightCapacity = maxWeightCapacity;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }
}