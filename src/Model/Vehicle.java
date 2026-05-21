package Model;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle")
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

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Employee actualDriver;

    public Vehicle() {
    }

    public Vehicle(String id, String vehicleSign, double vehicleMiles,
                   double fuelAmount, double tankSize, Employee actualDriver) {
        this.id = id;
        this.vehicleSign = vehicleSign;
        this.vehicleMiles = vehicleMiles;
        this.fuelAmount = fuelAmount;
        this.tankSize = tankSize;
        this.actualDriver = actualDriver;
    }

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

