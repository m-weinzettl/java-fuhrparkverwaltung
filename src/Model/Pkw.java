package Model;

import jakarta.persistence.*;

@Entity
@Table(name = "pkw")
@PrimaryKeyJoinColumn(name = "id")
public class Pkw extends Vehicle {
    @Column(name = "number_of_doors")
    private int numberDoors;
    @Column(name = "vignette")
    private boolean vignette;

    public Pkw(String id, String vehicleSign, double vehicleMiles, double fuelAmount,
               double tankSize, Employee actualDriver, int numberDoors, boolean vignette) {

        super(id, vehicleSign, vehicleMiles, fuelAmount, tankSize, actualDriver);

        this.numberDoors = numberDoors;
        this.vignette = vignette;
    }
    public boolean isVignette() {
        return vignette;
    }

    public void setVignette(boolean vignette) {
        this.vignette = vignette;
    }

    public int getNumberDoors() {
        return numberDoors;
    }

    public void setNumberDoors(int numberDoors) {
        this.numberDoors = numberDoors;
    }

    public Pkw() {
    }
}


