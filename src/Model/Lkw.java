package Model;

public class Lkw extends Vehicle {

    private double maxWeightCapacity;
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
}

/// /maxWeightCapacity : double (in Tonns)
/// /
/// / loadCapacity : double (in m^3)