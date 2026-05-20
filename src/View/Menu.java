package View;

import Model.Employee;
import Model.Vehicle;
import Model.VehicleFactory;

import java.util.LinkedList;
import java.util.Scanner;

public class Menu {
    private final Scanner input = new Scanner(System.in);

    public void showMenu(VehicleFactory vehicleFactory) {
        int option;

        do {
            System.out.println("\n=== Fuhrparkverwaltung ===");
            System.out.println("1. Vehicle hinzufügen");
            System.out.println("2. Mitarbeiter hinzufügen");
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
                    handleCreateVehicle(vehicleFactory);
                    break;
                case 2:
                    handleAddEmployee();
                    break;
                case 4:
                    System.out.println("Anwendung wird heruntergefahren. Auf Wiedersehen!");
                    break;
                default:
                    System.out.println("Diese Option existiert nicht.");
            }
        } while (option != 4);
    }

    private void handleCreateVehicle(VehicleFactory vehicleFactory) {
        System.out.print("Typ (PKW, LKW, MOTORCYCLE): ");
        String type = input.nextLine();
        System.out.print("ID: ");
        String id = input.nextLine();
        System.out.print("Kennzeichen: ");
        String vehicleSign = input.nextLine();
        System.out.print("Kilometerstand: ");
        double vehicleMiles = input.nextDouble();
        System.out.print("Füllstand: ");
        double fuelAmount = input.nextDouble();
        System.out.print("Tankgröße: ");
        double tankSize = input.nextDouble();
        input.nextLine();

        Vehicle newVehicle = vehicleFactory.createVehicle(type, id, vehicleSign, vehicleMiles, fuelAmount, tankSize);

        if (newVehicle != null) {
            System.out.println("Fahrzeug erfolgreich angelegt!");
        }
    }

    private void handleAddEmployee() {
        System.out.print("ID: ");
        String employeeId = input.nextLine();
        System.out.print("Vorname: ");
        String firstName = input.nextLine();
        System.out.print("Nachname: ");
        String lastName = input.nextLine();

        LinkedList<String> licenseList = new LinkedList<>();
        System.out.print("Führerschein eingeben (oder leer lassen zum Beenden): ");
        String license = input.nextLine();
        while (!license.isEmpty()) {
            licenseList.add(license);
            System.out.print("Weiteren Führerschein eingeben (oder leer lassen zum Beenden): ");
            license = input.nextLine();
        }

        Employee newEmployee = new Employee(employeeId, firstName, lastName, licenseList);
        System.out.println("Mitarbeiter " + newEmployee.getFirstName() + " erfolgreich angelegt!");
    }
}