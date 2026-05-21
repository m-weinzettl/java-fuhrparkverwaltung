package Controller;

import Model.Employee;
import Model.Vehicle;
import Model.VehicleFactory;
import jakarta.persistence.EntityManager;

import java.util.LinkedList;
import java.util.Scanner;

public class ParkManager {
    EntityManager entityManager;
    VehicleFactory vehicleFactory;

    private final Scanner input = new Scanner(System.in);

    public ParkManager(EntityManager entityManager, VehicleFactory vehicleFactory) {
        this.entityManager = entityManager;
        this.vehicleFactory = vehicleFactory;
    }

    public void handleAddEmployee(EntityManager entityManager) {
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
        entityManager.getTransaction().begin();
        entityManager.persist(newEmployee);
        entityManager.getTransaction().commit();
        System.out.println("Mitarbeiter " + newEmployee.getFirstName() + " erfolgreich angelegt!");
    }

    public void handleCreateVehicle(VehicleFactory vehicleFactory, EntityManager entityManager) {
        System.out.print("Typ (PKW, LKW, MOTORCYCLE): ");
        String type = input.nextLine().toUpperCase();
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

        Vehicle newVehicle = null;

        switch (type) {
            case "PKW":
                System.out.print("Anzahl Türen: ");
                int numberOfDoors = input.nextInt();
                System.out.print("Vignette y/n: ");
                char vignetteChar = input.next().charAt(0);
                input.nextLine();
                boolean vignette = (vignetteChar == 'y' || vignetteChar == 'Y');

                newVehicle = vehicleFactory.createPkw(id, vehicleSign, vehicleMiles, fuelAmount, tankSize,
                        null, numberOfDoors, vignette);
                break;

            case "LKW":
                System.out.print("Max erlaubtes Gewicht in t: ");
                double maxWeightCapacity = input.nextDouble();
                System.out.print("Max Zuladung in t: ");
                double loadCapacity = input.nextDouble();
                input.nextLine();

                newVehicle = vehicleFactory.createLkw(id, vehicleSign, vehicleMiles, fuelAmount,
                        tankSize, null, maxWeightCapacity, loadCapacity);
                break;

            case "MOTORCYCLE":
                System.out.print("Helmkoffer y/n: ");
                char helmetChar = input.next().charAt(0);
                input.nextLine();
                boolean helmetCase = (helmetChar == 'y' || helmetChar == 'Y');
                System.out.print("Benötigte Führerscheinklasse: ");
                String license = input.nextLine();

                newVehicle = vehicleFactory.createMotorcycle(id, vehicleSign, vehicleMiles, fuelAmount,
                        tankSize, null, helmetCase, license);
                break;

            default:
                System.out.println("Unbekannter Fahrzeugtyp!");
        }

        if (newVehicle != null) {
            entityManager.getTransaction().begin();
            entityManager.persist(newVehicle);
            entityManager.getTransaction().commit();
            System.out.println("Fahrzeug erfolgreich angelegt!");
        }
    }

    public void handleAssignEmployeeToVehicle(EntityManager entityManager) {
        System.out.println("--- Liste aller Fahrzeuge ---");

        java.util.List<Vehicle> vehicles = entityManager.createQuery(
                "SELECT v FROM Vehicle v", Vehicle.class).getResultList();

        if (vehicles.isEmpty()) {
            System.out.println("Keine Fahrzeuge in der Datenbank vorhanden.");
            return;
        }
        for(Vehicle v : vehicles) {
            System.out.println("ID: " + v.getId() + " | Kennzeichen: "
                    + v.getVehicleSign() + " | Typ: " + v.getClass().getSimpleName());
        }

        System.out.println("-----------------------------");
        System.out.print("Gibe die Fahrzeug-ID ein: ");
        String vehicleId = input.nextLine();

        System.out.println("--- Liste aller Mitarbeiter ---");

        java.util.List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();

        if (employees.isEmpty()) {
            System.out.println("Keine Mitarbeiter in der Datenbank vorhanden.");
            return;
        }
        for(Employee employee: employees) {
            System.out.println("ID: " + employee.getEmployeeId() +
                    " | Name: " + employee.getLastName() + " | Typ: " +
                    employee.getClass().getSimpleName());
        }
        System.out.print("Gib die Mitarbeiter-ID ein: ");
        String employeeId = input.nextLine();

        Vehicle vehicle = entityManager.find(Vehicle.class, vehicleId);
        Employee employee = entityManager.find(Employee.class, employeeId);

        if (vehicle == null) {
            System.out.println("Fehler: Fahrzeug mit ID '" + vehicleId + "' nicht gefunden.");
            return;
        }

        if (employee == null) {
            System.out.println("Fehler: Mitarbeiter mit ID '" + employeeId + "' nicht gefunden.");
            return;
        }

        entityManager.getTransaction().begin();
        vehicle.setActualDriver(employee);
        entityManager.getTransaction().commit();

        System.out.println("Erfolg: Fahrzeug " + vehicle.getVehicleSign() +
                " wurde " + employee.getFirstName() + " " + employee.getLastName() + " zugewiesen!");
    }

}
