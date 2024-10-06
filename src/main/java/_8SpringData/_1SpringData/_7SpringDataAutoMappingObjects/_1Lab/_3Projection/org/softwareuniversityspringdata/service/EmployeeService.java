package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._3Projection.org.softwareuniversityspringdata.service;

import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._3Projection.org.softwareuniversityspringdata.data.entities.Employee;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._3Projection.org.softwareuniversityspringdata.service.dtos.EmployeeDTO;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    String hireNewEmployee(Employee employee);

    String hireNewEmployeeByDTO(EmployeeDTO employeeDTO);

    String getInfoAboutNewEmployee(String employeeName);

    String setEmployeesToManager(Employee manager, List<Employee> employees);

    List<EmployeeDTO> findInfoForEmployeesBornBefore(LocalDate birthday);
}
