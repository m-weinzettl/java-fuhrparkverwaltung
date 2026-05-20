package Model;

public class Pkw extends Vehicle {
    private int numberDoors;
    private boolean vignette;

    public Pkw(String id, String vehicleSign, double vehicleMiles, double fuelAmount,
               double tankSize, Employee actualDriver, int numberDoors, boolean vignette) {

        super(id, vehicleSign, vehicleMiles, fuelAmount, tankSize, actualDriver);

        this.numberDoors = numberDoors;
        this.vignette = vignette;
    }
}


