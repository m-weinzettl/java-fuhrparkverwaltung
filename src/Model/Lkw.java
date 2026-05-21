package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "lkw")
@PrimaryKeyJoinColumn(name = "id")
public class Lkw extends Vehicle {
    @Column(name = "max_weight_capacity")
    private double maxWeightCapacity;
    @Column(name = "load_capacity")
    private double loadCapacity;

    public Lkw(String id, String vehicleSign, double vehicleMiles, double fuelAmount,
               double tankSize, Employee actualDriver, double maxWeightCapacity, double loadCapacity) {

        super(id, vehicleSign, vehicleMiles, fuelAmount, tankSize, actualDriver);

        this.maxWeightCapacity = maxWeightCapacity;
        this.loadCapacity = loadCapacity;

    }

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

    public Lkw() {
    }
}

/// /maxWeightCapacity : double (in Tonns)
/// /
/// / loadCapacity : double (in m^3)