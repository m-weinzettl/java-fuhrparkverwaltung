package Controller;

import Model.Employee;
import Model.Vehicle;
import Model.VehicleFactory;
import jakarta.persistence.EntityManager;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Der ParkManager agiert als Controller im MVC-Entwurfsmuster.
 * Er steuert die Geschäftslogik der Fuhrparkverwaltung, verarbeitet Benutzereingaben,
 * nutzt die VehicleFactory zur Objekterzeugung und kommuniziert über JPA mit der Datenbank.
 */
public class ParkManager {
    // Instanzvariablen für die übergebenen Abhängigkeiten (Dependency Injection)
    EntityManager entityManager;
    VehicleFactory vehicleFactory;

    // Ein zentraler Scanner für die Benutzereingaben innerhalb des Controllers
    private final Scanner input = new Scanner(System.in);

    /**
     * Konstruktor für Dependency Injection.
     * Ermöglicht es, dem Manager die benötigten Werkzeuge von außen bereitzustellen.
     *
     * @param entityManager  Der JPA-EntityManager für Persistenz-Operationen
     * @param vehicleFactory Die Fabrik zur Erzeugung von Fahrzeug-Instanzen
     */
    public ParkManager(EntityManager entityManager, VehicleFactory vehicleFactory) {
        this.entityManager = entityManager;
        this.vehicleFactory = vehicleFactory;
    }

    /**
     * Erfragt die Daten für einen neuen Mitarbeiter über die Konsole,
     * sammelt Führerscheine dynamisch und speichert das Objekt in der Datenbank ab.
     *
     * @param entityManager Der aktuelle JPA-EntityManager für die Datenbanktransaktion
     */
    public void handleAddEmployee(EntityManager entityManager) {
        System.out.print("ID: ");
        String employeeId = input.nextLine();
        System.out.print("Vorname: ");
        String firstName = input.nextLine();
        System.out.print("Nachname: ");
        String lastName = input.nextLine();

        // Dynamische Liste, um eine beliebige Anzahl von Führerscheinklassen zu erfassen
        LinkedList<String> licenseList = new LinkedList<>();
        System.out.print("Führerschein eingeben (oder leer lassen zum Beenden): ");
        String license = input.nextLine();
        // Schleife läuft so lange, bis der Benutzer einfach Enter drückt
        while (!license.isEmpty()) {
            licenseList.add(license);
            System.out.print("Weiteren Führerschein eingeben (oder leer lassen zum Beenden): ");
            license = input.nextLine();
        }

        // Neues Datenmodell-Objekt (Entität) erstellen
        Employee newEmployee = new Employee(employeeId, firstName, lastName, licenseList);

        // Datenbanktransaktion starten, Objekt persistieren (speichern) und Transaktion abschließen
        entityManager.getTransaction().begin();
        entityManager.persist(newEmployee);
        entityManager.getTransaction().commit();
        System.out.println("Mitarbeiter " + newEmployee.getFirstName() + " erfolgreich angelegt!");
    }

    /**
     * Liest die allgemeinen Fahrzeugdaten ein, unterscheidet nach Fahrzeugtyp,
     * erfragt typspezifische Attribute und nutzt die VehicleFactory für die Erstellung.
     * Das erzeugte Fahrzeug wird anschließend in der Datenbank gespeichert.
     *
     * @param vehicleFactory Die Fabrik zur Instanziierung der Unterklassen
     * @param entityManager  Der aktuelle JPA-EntityManager für die Datenbanktransaktion
     */
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
        input.nextLine(); // Konsolen-Buffer nach numerischen Eingaben leeren

        Vehicle newVehicle = null;

        // Fallunterscheidung basierend auf dem gewählten Fahrzeugtyp
        switch (type) {
            case "PKW":
                System.out.print("Anzahl Türen: ");
                int numberOfDoors = input.nextInt();
                System.out.print("Vignette y/n: ");
                char vignetteChar = input.next().charAt(0);
                input.nextLine(); // Buffer leeren
                boolean vignette = (vignetteChar == 'y' || vignetteChar == 'Y');

                // Delegation der Objekterzeugung an die Factory
                newVehicle = vehicleFactory.createPkw(id, vehicleSign, vehicleMiles, fuelAmount, tankSize,
                        null, numberOfDoors, vignette);
                break;

            case "LKW":
                System.out.print("Max erlaubtes Gewicht in t: ");
                double maxWeightCapacity = input.nextDouble();
                System.out.print("Max Zuladung in t: ");
                double loadCapacity = input.nextDouble();
                input.nextLine(); // Buffer leeren

                // Delegation der Objekterzeugung an die Factory
                newVehicle = vehicleFactory.createLkw(id, vehicleSign, vehicleMiles, fuelAmount,
                        tankSize, null, maxWeightCapacity, loadCapacity);
                break;

            case "MOTORCYCLE":
                System.out.print("Helmkoffer y/n: ");
                char helmetChar = input.next().charAt(0);
                input.nextLine(); // Buffer leeren
                boolean helmetCase = (helmetChar == 'y' || helmetChar == 'Y');
                System.out.print("Benötigte Führerscheinklasse: ");
                String license = input.nextLine();

                // Delegation der Objekterzeugung an die Factory
                newVehicle = vehicleFactory.createMotorcycle(id, vehicleSign, vehicleMiles, fuelAmount,
                        tankSize, null, helmetCase, license);
                break;

            default:
                System.out.println("Unbekannter Fahrzeugtyp!");
        }

        // Wenn ein gültiges Fahrzeugobjekt von der Factory erzeugt wurde, wird es persistiert
        if (newVehicle != null) {
            entityManager.getTransaction().begin();
            entityManager.persist(newVehicle);
            entityManager.getTransaction().commit();
            System.out.println("Fahrzeug erfolgreich angelegt!");
        }
    }

    /**
     * Lädt alle vorhandenen Fahrzeuge und Mitarbeiter per JPQL aus der Datenbank,
     * zeigt diese als Auswahlliste an und verknüpft einen Fahrer (Employee) mit einem Fahrzeug.
     *
     * @param entityManager Der aktuelle JPA-EntityManager für Abfragen und Transaktionen
     */
    public void handleAssignEmployeeToVehicle(EntityManager entityManager) {
        System.out.println("--- Liste aller Fahrzeuge ---");

        // JPQL-Query, um alle registrierten Fahrzeuge aus der Datenbank abzufragen
        java.util.List<Vehicle> vehicles = entityManager.createQuery(
                "SELECT v FROM Vehicle v", Vehicle.class).getResultList();

        if (vehicles.isEmpty()) {
            System.out.println("Keine Fahrzeuge in der Datenbank vorhanden.");
            return;
        }
        // Ausgabe der Fahrzeugliste auf der Konsole
        for (Vehicle v : vehicles) {
            System.out.println("ID: " + v.getId() + " | Kennzeichen: "
                    + v.getVehicleSign() + " | Typ: " + v.getClass().getSimpleName());
        }

        System.out.println("-----------------------------");
        System.out.print("Gibe die Fahrzeug-ID ein: ");
        String vehicleId = input.nextLine();

        System.out.println("--- Liste aller Mitarbeiter ---");

        // JPQL-Query, um alle registrierten Mitarbeiter aus der Datenbank abzufragen
        java.util.List<Employee> employees = entityManager.createQuery(
                "SELECT e FROM Employee e", Employee.class).getResultList();

        if (employees.isEmpty()) {
            System.out.println("Keine Mitarbeiter in der Datenbank vorhanden.");
            return;
        }
        // Ausgabe der Mitarbeiterliste auf der Konsole
        for (Employee employee : employees) {
            System.out.println("ID: " + employee.getEmployeeId() +
                    " | Name: " + employee.getLastName() + " | Typ: " +
                    employee.getClass().getSimpleName());
        }
        System.out.print("Gib die Mitarbeiter-ID ein: ");
        String employeeId = input.nextLine();

        // Suchen der konkreten Entitäten im Persistence Context anhand ihrer IDs
        Vehicle vehicle = entityManager.find(Vehicle.class, vehicleId);
        Employee employee = entityManager.find(Employee.class, employeeId);

        // Validierung, ob die eingegebenen IDs überhaupt in der Datenbank existieren
        if (vehicle == null) {
            System.out.println("Fehler: Fahrzeug mit ID '" + vehicleId + "' nicht gefunden.");
            return;
        }

        if (employee == null) {
            System.out.println("Fehler: Mitarbeiter mit ID '" + employeeId + "' nicht gefunden.");
            return;
        }

        // Aktualisierung der Beziehung: Setzt den geladenen Mitarbeiter als aktuellen Fahrer
        entityManager.getTransaction().begin();
        vehicle.setActualDriver(employee);
        entityManager.getTransaction().commit();

        System.out.println("Erfolg: Fahrzeug " + vehicle.getVehicleSign() +
                " wurde " + employee.getFirstName() + " " + employee.getLastName() + " zugewiesen!");
    }
}