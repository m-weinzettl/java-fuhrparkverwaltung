import Controller.ParkManager;
import Model.VehicleFactory;
import View.Menu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Die Main-Klasse dient als zentraler Einstiegspunkt der Anwendung.
 * Sie kümmert sich um die Initialisierung der JPA-Infrastruktur (Hibernate)
 * und verbindet die Komponenten via Dependency Injection.
 */
class Main {
    public static void main(String[] args) {
        // Erzeugt die EntityManagerFactory basierend auf der persistence.xml Konfiguration
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("FuhrparkverwaltungDB");

        // Erstellt den EntityManager für die Abwicklung der Datenbankoperationen
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // DEPENDENCY INJECTION (Konstruktor-Injektion):
        // Der ParkManager erzeugt seine Abhängigkeiten (EntityManager, VehicleFactory) nicht selbst,
        // sondern bekommt sie von außen über den Konstruktor injiziert.
        ParkManager parkManager = new ParkManager(entityManager, new VehicleFactory());

        // DEPENDENCY INJECTION:
        // Das Menu benötigt den ParkManager zum Arbeiten und bekommt diesen hier übergeben.
        Menu menu = new Menu(parkManager);

        // DEPENDENCY INJECTION (Methoden-Injektion):
        // Die VehicleFactory und der EntityManager werden als temporäre Abhängigkeiten 
        // direkt an die Methode übergeben.
        menu.showMenu(new VehicleFactory(), entityManager);

        // Schließt die Verbindungen zur Datenbank ordnungsgemäß nach Beenden des Programms
        entityManager.close();
        entityManagerFactory.close();
    }
}