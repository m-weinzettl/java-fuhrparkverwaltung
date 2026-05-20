package Model;

import java.util.List;

public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private List<String> licenseClasses;

    public Employee(String employeeId, String firstName, String lastName, List<String> licenseClasses) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.licenseClasses = licenseClasses;
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

    public List<String> getLicenseClasses() {
        return licenseClasses;
    }

    public void setLicenseClasses(List<String> licenseClasses) {
        this.licenseClasses = licenseClasses;
    }
}