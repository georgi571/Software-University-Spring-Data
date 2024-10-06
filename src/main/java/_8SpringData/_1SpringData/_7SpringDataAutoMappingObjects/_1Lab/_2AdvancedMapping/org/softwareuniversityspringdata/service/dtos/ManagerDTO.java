package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._2AdvancedMapping.org.softwareuniversityspringdata.service.dtos;

import java.util.ArrayList;
import java.util.List;

public class ManagerDTO {
    private String firstName;
    private String lastName;
    private List<EmployeeDTO> employees;

    public ManagerDTO() {
        this.employees = new ArrayList<>();
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

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return String.format("%s %s | Employees: %d%n    -%s",
                this.firstName,
                this.lastName,
                this.employees.size(),
                String.join("\n    -", employees.stream().map(EmployeeDTO::toString).toList()));
    }
}
