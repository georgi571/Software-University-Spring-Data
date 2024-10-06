package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._3Projection.org.softwareuniversityspringdata.service.dtos;

import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._3Projection.org.softwareuniversityspringdata.data.entities.Employee;

public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private double salary;
    private Employee manager;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String firstName, String lastName, double salary, Employee manager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.manager = manager;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        String managerLastName = "";
        if (this.manager == null) {
            managerLastName = "[no manager]";
        } else {
            managerLastName = this.manager.getLastName();
        }
        return String.format("%s %s %.2f - Manager: %s", this.firstName, this.lastName, this.salary, managerLastName);
    }
}
