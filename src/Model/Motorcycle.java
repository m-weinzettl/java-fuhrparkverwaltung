package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "motorcycle")
@PrimaryKeyJoinColumn(name = "id")
public class Motorcycle extends Vehicle {
    @Column(name = "helmet_case")
    private boolean helmetCase;
    @Column(name = "license")
    private String license;

    public Motorcycle(String id, String vehicleSign, double vehicleMiles, double fuelAmount,
                      double tankSize, Employee actualDriver, boolean helmetCase, String license) {
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

    public Motorcycle() {
    }
}
