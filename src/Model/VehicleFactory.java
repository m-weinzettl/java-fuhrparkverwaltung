package Model;

public abstract class VehicleFactory {
    public Vehicle createVehicle(String type, String id, String vehicleSign,
                                 double vehicleMiles, double fuelAmount, double tankSize) {
        if (type == null) {
            return null;
        }
        switch (type.toUpperCase()){
            case "PKW":
                return new Pkw(id, vehicleSign, vehicleMiles, fuelAmount, tankSize, , true);
                break;
            case "LKW":
                return new Lkw(id, vehicleSign, vehicleMiles, fuelAmount, tankSize, , true);
                break;
            case "MOTORCYCLE":


        }
    }
}
//+ createVehicle(id : String (uuid)
//
//vehicleSign : String
//
//vehicleMiles : double)