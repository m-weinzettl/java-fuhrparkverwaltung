package Model;

public class Motorcycle extends Vehicle {
    private boolean helmetCase;
    private String license;

    public Motorcycle(String id, String vehicleSign, double vehicleMiles, double fuelAmount,
                      double tankSize, Employee actualDriver, boolean helmetCase, String license){
        super(id, vehicleSign, vehicleMiles, fuelAmount, tankSize, actualDriver);

        this.helmetCase = helmetCase;
        this.license = license;
    }
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

//elmetCase : boolean
//
// license : String (z.B. A1, A2, A)