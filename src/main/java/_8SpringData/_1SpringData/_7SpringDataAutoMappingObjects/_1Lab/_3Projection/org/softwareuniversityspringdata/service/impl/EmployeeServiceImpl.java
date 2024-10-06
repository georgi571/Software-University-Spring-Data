package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._3Projection.org.softwareuniversityspringdata.service.impl;

import org.modelmapper.ModelMapper;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._3Projection.org.softwareuniversityspringdata.data.entities.Employee;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._3Projection.org.softwareuniversityspringdata.data.repositories.EmployeeRepository;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._3Projection.org.softwareuniversityspringdata.service.EmployeeService;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._3Projection.org.softwareuniversityspringdata.service.dtos.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(ModelMapper modelMapper, EmployeeRepository employeeRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public String hireNewEmployee(Employee employee) {
        this.employeeRepository.saveAndFlush(employee);
        return String.format("Employee %s %s was hired", employee.getFirstName(), employee.getLastName());
    }

    @Override
    public String hireNewEmployeeByDTO(EmployeeDTO employeeDTO) {
        Employee employee = this.modelMapper.map(employeeDTO, Employee.class);
        this.employeeRepository.saveAndFlush(employee);
        return String.format("Employee %s %s was hired", employeeDTO.getFirstName(), employeeDTO.getLastName());
    }

    @Override
    public String getInfoAboutNewEmployee(String employeeName) {
        Employee employee = this.employeeRepository.findByFirstName(employeeName);
        return this.modelMapper.map(employee, EmployeeDTO.class).toString();
    }

    @Override
    public String setEmployeesToManager(Employee manager, List<Employee> employees) {
        manager.setListOfEmployees(employees);
        for (Employee employee : employees) {
            employee.setManager(manager);
            this.employeeRepository.saveAndFlush(employee);
        }
        this.employeeRepository.saveAndFlush(manager);
        return String.format("To manager %s %s was added %d new employees", manager.getFirstName(), manager.getLastName(), employees.size());
    }

    @Override
    public List<EmployeeDTO> findInfoForEmployeesBornBefore(LocalDate birthday) {
        List<EmployeeDTO> employees = this.employeeRepository.findAllByBirthdayBefore(birthday);
        return employees;
    }
}
