package Model;

import java.util.LinkedList;
import java.util.List;

public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private LinkedList<String> licenseList;

    public Employee(String employeeId, String firstName, String lastName, LinkedList<String> licenseList) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.licenseList = licenseList != null ? licenseList : new LinkedList<>();
    }

    public void addLicense(String license) {
        licenseList.add(license);
    }

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

    public void setLicenseList(LinkedList<String> licenseList) {
        this.licenseList = licenseList;
    }
}