package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._1SimpleMapping.org.softwareuniversityspringdata.service;

import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._1SimpleMapping.org.softwareuniversityspringdata.data.entities.Employee;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._1SimpleMapping.org.softwareuniversityspringdata.service.dtos.EmployeeDTO;

public interface EmployeeService {
    String hireNewEmployee(Employee employee);

    String hireNewEmployeeByDTO(EmployeeDTO employeeDTO);

    String getInfoAboutNewEmployee(String employeeName);
}
