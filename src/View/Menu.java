package View;

import Controller.ParkManager;
import jakarta.persistence.EntityManager;
import Model.VehicleFactory;

import java.util.Scanner;

public class Menu {
    private final Scanner input = new Scanner(System.in);
    private final ParkManager parkManager;

    public Menu(ParkManager parkManager) {
        this.parkManager = parkManager;
    }

    public void showMenu(VehicleFactory vehicleFactory, EntityManager entityManager) {
        int option;


        do {
            System.out.println("\n=== Fuhrparkverwaltung ===");
            System.out.println("1. Vehicle hinzufügen");
            System.out.println("2. Mitarbeiter hinzufügen");
            System.out.println("3. Mitarbeiter Fahrzeug zuweisen");
            System.out.println("4. Beenden");
            System.out.print("Deine Auswahl: ");

            while (!input.hasNextInt()) {
                System.out.print("Bitte gib eine gültige Zahl ein: ");
                input.next();
            }

            option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1:
                    parkManager.handleCreateVehicle(vehicleFactory, entityManager);
                    break;
                case 2:
                    parkManager.handleAddEmployee(entityManager);
                    break;
                case 3:
                    parkManager.handleAssignEmployeeToVehicle(entityManager);
                    break;
                case 4:
                    System.out.println("Anwendung wird heruntergefahren. Auf Wiedersehen!");
                    break;
                default:
                    System.out.println("Diese Option existiert nicht.");
            }
        } while (option != 4);
    }
}
