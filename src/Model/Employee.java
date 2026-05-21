package Model;

import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Die Klasse Employee repräsentiert einen Mitarbeiter im System.
 * Sie dient als JPA-Entität (Datenmodell) für die Speicherung und Verwaltung
 * von Mitarbeiterdaten inklusive ihrer Führerscheinklassen.
 */
@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    // @ElementCollection erstellt automatisch eine separate Hilfstabelle in der Datenbank,
    // um einfache Datentypen (wie Strings) als Liste zu speichern, ohne eine eigene Entität dafür zu benötigen.
    @ElementCollection
    // Definiert den Namen der Hilfstabelle und den Fremdschlüssel (employee_id) zur Verknüpfung.
    @CollectionTable(name = "employee_licenses", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "license")
    private List<String> licenseList;

    /**
     * Der Standardkonstruktor (No-Arg-Constructor).
     * Wird zwingend von JPA benötigt, um Objekte beim Auslesen aus der Datenbank zu instanziieren.
     * Initialisiert die Liste, um NullPointerExceptions zu vermeiden.
     */
    public Employee() {
        this.licenseList = new LinkedList<>();
    }

    /**
     * Konstruktor zur manuellen Erstellung eines Mitarbeiters mit allen Attributen.
     *
     * @param employeeId  Eindeutige ID des Mitarbeiters (Primärschlüssel)
     * @param firstName   Der Vorname des Mitarbeiters
     * @param lastName    Der Nachname des Mitarbeiters
     * @param licenseList Die Liste der vorhandenen Führerscheinklassen
     */
    public Employee(String employeeId, String firstName, String lastName, List<String> licenseList) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        // Null-Check, um sicherzustellen, dass die Liste niemals 'null' ist
        this.licenseList = licenseList != null ? licenseList : new LinkedList<>();
    }

    /**
     * Fügt der Liste des Mitarbeiters eine neue Führerscheinklasse hinzu.
     *
     * @param license Die hinzuzufügende Führerscheinklasse (z.B. "B", "CE")
     */
    public void addLicense(String license) {
        licenseList.add(license);
    }

    // ==========================================
    // Getter und Setter (Zugriffsmethoden)
    // ==========================================

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getLicenseList() {
        return licenseList;
    }

    public void setLicenseList(List<String> licenseList) {
        this.licenseList = licenseList;
    }
}