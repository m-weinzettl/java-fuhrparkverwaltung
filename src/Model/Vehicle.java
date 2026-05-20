package Model;

public abstract class Vehicle {
    private String id;
    private String vehicleSign;
    private double vehicleMiles;
    private double fuelAmount;
    private double tankSize;
    private Employee actualDriver;

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


//id : String (uuid)
//
//vehicleSign : String
//
//vehicleMiles : double
//
//fuelAmount : double
//
//tankSize : double
//
//actualDriver : employee (Verknüpfung zur Mitarbeiter-Klasse)