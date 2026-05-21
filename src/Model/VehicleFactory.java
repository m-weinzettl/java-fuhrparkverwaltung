package Model;

public class VehicleFactory {

    public Pkw createPkw(String id, String vehicleSign, double vehicleMiles,
                         double fuelAmount, double tankSize, Employee actualDriver,
                         int numberOfDoors, boolean vignette) {
        return new Pkw(id, vehicleSign, vehicleMiles, fuelAmount, tankSize,
                actualDriver, numberOfDoors, vignette);
    }

    public Lkw createLkw(String id, String vehicleSign, double vehicleMiles,
                         double fuelAmount, double tankSize, Employee actualDriver,
                         double maxWeightCapacity, double loadCapacity) {
        return new Lkw(id, vehicleSign, vehicleMiles, fuelAmount, tankSize,
                actualDriver, maxWeightCapacity, loadCapacity);
    }

    public Motorcycle createMotorcycle(String id, String vehicleSign, double vehicleMiles,
                                       double fuelAmount, double tankSize, Employee actualDriver,
                                       boolean helmetCase, String license) {
        return new Motorcycle(id, vehicleSign, vehicleMiles, fuelAmount, tankSize,
                actualDriver, helmetCase, license);
    }
}