package Model;

import jakarta.persistence.*;

/**
 * Die abstrakte Basisklasse Vehicle repräsentiert ein Fahrzeug im System.
 * Sie dient als übergeordnetes Datenmodell (Entität) für konkrete Fahrzeugtypen
 * und definiert die gemeinsamen Attribute sowie die JPA-Vererbungsstrategie.
 */
@Entity
@Table(name = "vehicle")
// JOINED bedeutet: Für die Basisklasse und jede Unterklasse wird eine eigene Tabelle in der DB angelegt.
// Die Tabellen werden über den Primärschlüssel (id) miteinander verknüpft.
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehicle {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "vehicle_sign")
    private String vehicleSign;

    @Column(name = "vehicle_miles")
    private double vehicleMiles;

    @Column(name = "fuel_amount")
    private double fuelAmount;

    @Column(name = "tank_size")
    private double tankSize;

    // Definiert eine n:1-Beziehung (Viele Fahrzeuge können demselben Fahrer zugewiesen sein).
    // In der Datenbank wird eine Fremdschlüsselspalte 'driver_id' in der Vehicle-Tabelle erzeugt.
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Employee actualDriver;

    /**
     * Der Standardkonstruktor (No-Arg-Constructor).
     * Dieser ist zwingend erforderlich, da JPA/Hibernate ihn für die
     * Instanziierung der Objekte beim Laden aus der Datenbank benötigt.
     */
    public Vehicle() {
    }

    /**
     * Konstruktor zur Erstellung eines Fahrzeugs mit allen gemeinsamen Attributen.
     *
     * @param id           Eindeutige ID des Fahrzeugs (Primärschlüssel)
     * @param vehicleSign  Das Kennzeichen des Fahrzeugs
     * @param vehicleMiles Der aktuelle Kilometerstand
     * @param fuelAmount   Der aktuelle Tankfüllstand
     * @param tankSize     Das maximale Tankvolumen
     * @param actualDriver Der dem Fahrzeug aktuell zugewiesene Fahrer
     */
    public Vehicle(String id, String vehicleSign, double vehicleMiles,
                   double fuelAmount, double tankSize, Employee actualDriver) {
        this.id = id;
        this.vehicleSign = vehicleSign;
        this.vehicleMiles = vehicleMiles;
        this.fuelAmount = fuelAmount;
        this.tankSize = tankSize;
        this.actualDriver = actualDriver;
    }

    // ==========================================
    // Getter und Setter (Zugriffsmethoden)
    // ==========================================

    public String getVehicleSign() {
        return vehicleSign;
    }

    public void setVehicleSign(String vehicleSign) {
        this.vehicleSign = vehicleSign;
    }

    public Employee getActualDriver() {
        return actualDriver;
    }

    public void setActualDriver(Employee actualDriver) {
        this.actualDriver = actualDriver;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getVehicleMiles() {
        return vehicleMiles;
    }

    public void setVehicleMiles(double vehicleMiles) {
        this.vehicleMiles = vehicleMiles;
    }

    public double getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(double fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public double getTankSize() {
        return tankSize;
    }

    public void setTankSize(double tankSize) {
        this.tankSize = tankSize;
    }
}