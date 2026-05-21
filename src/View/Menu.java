package View;

import Controller.ParkManager;
import jakarta.persistence.EntityManager;
import Model.VehicleFactory;

import java.util.Scanner;

/**
 * Die Menu-Klasse stellt die View (Präsentationsschicht) der Anwendung dar.
 * Sie kümmert sich ausschließlich um die Interaktion mit dem Benutzer über die Konsole,
 * gibt Textausgaben aus und liest Eingaben ein.
 */
public class Menu {
    private final Scanner input = new Scanner(System.in);
    private final ParkManager parkManager;

    /**
     * Konstruktor für die Dependency Injection.
     * Erwartet den zuständigen Controller (ParkManager), um die Programmlogik auszuführen.
     *
     * @param parkManager Der Controller für die Fuhrparkverwaltung
     */
    public Menu(ParkManager parkManager) {
        this.parkManager = parkManager;
    }

    /**
     * Startet die textbasierte Benutzeroberfläche und hält das Menü in einer Schleife aktiv,
     * bis der Benutzer die Anwendung explizit beendet.
     *
     * @param vehicleFactory Die Fabrik zur Fahrzeugerstellung (wird an den Controller durchgereicht)
     * @param entityManager Der JPA-EntityManager für Datenbankzugriffe (wird an den Controller durchgereicht)
     */
    public void showMenu(VehicleFactory vehicleFactory, EntityManager entityManager) {
        int option;

        do {
            System.out.println("\n=== Fuhrparkverwaltung ===");
            System.out.println("1. Vehicle hinzufügen");
            System.out.println("2. Mitarbeiter hinzufügen");
            System.out.println("3. Mitarbeiter Fahrzeug zuweisen");
            System.out.println("4. Beenden");
            System.out.print("Deine Auswahl: ");

            // Eingabe-Validierung: Verhindert Abstürze, falls der Benutzer keine Ganzzahl eingibt
            while (!input.hasNextInt()) {
                System.out.print("Bitte gib eine gültige Zahl ein: ");
                input.next();
            }

            option = input.nextInt();
            input.nextLine(); // Konsolen-Buffer leeren, um nachfolgende String-Eingaben nicht zu blockieren

            // Delegation der Menüauswahl an die entsprechenden Logik-Methoden des Controllers
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
        } while (option != 4); // Wiederholt das Menü so lange, bis Option 4 gewählt wird
    }
}