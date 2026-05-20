package Model;

public abstract class VehicleFactory {
    public Vehicle createVehicle(String type, String id, String vehicleSign,
                                 double vehicleMiles, double fuelAmount, double tankSize) {
        if (type == null) {
            return null;
        }
        switch (type.toUpperCase()) {
            case "PKW":
                return new Pkw(id, vehicleSign, vehicleMiles, fuelAmount,
                        tankSize, null, 4, true);
            case "LKW":
                return new Lkw(id, vehicleSign, vehicleMiles, fuelAmount,
                        tankSize, null, 12.0, 40.0);
            case "MOTORCYCLE":
                return new Motorcycle(id, vehicleSign, vehicleMiles, fuelAmount,
                        tankSize, null, true, "A");
            default:
                throw new IllegalArgumentException("Unbekannter Fahrzeugtyp: " + type);
        }
    }
}
//+ createVehicle(id : String (uuid)
//
//vehicleSign : String
//
//vehicleMiles : double)