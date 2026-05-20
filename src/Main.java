import Model.Employee;
import Model.Lkw;
import Model.Vehicle;
import Model.VehicleFactory;
import View.Menu;

import java.util.LinkedList;
import java.util.Vector;

class Main{
    public static void main(String[] args) {

        Menu menu = new Menu();
        menu.showMenu(new VehicleFactory());
    }
}
