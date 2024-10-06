package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._2AdvancedMapping.org.softwareuniversityspringdata.service;

import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._2AdvancedMapping.org.softwareuniversityspringdata.service.dtos.EmployeeDTO;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._2AdvancedMapping.org.softwareuniversityspringdata.data.entities.Employee;

import java.util.List;

public interface EmployeeService {
    String hireNewEmployee(Employee employee);

    String hireNewEmployeeByDTO(EmployeeDTO employeeDTO);

    String getInfoAboutNewEmployee(String employeeName);

    String setEmployeesToManager(Employee manager, List<Employee> employees);
}
