package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._2AdvancedMapping.org.softwareuniversityspringdata.service.dtos;

public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private double salary;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String firstName, String lastName, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
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

    @Override
    public String toString() {
        return String.format("%s %s %.2f", this.firstName, this.lastName, this.salary);
    }
}
