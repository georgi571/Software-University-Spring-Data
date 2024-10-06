package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._1SimpleMapping.org.softwareuniversityspringdata.service.impl;

import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._1SimpleMapping.org.softwareuniversityspringdata.data.entities.Employee;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._1SimpleMapping.org.softwareuniversityspringdata.data.repositories.EmployeeRepository;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._1SimpleMapping.org.softwareuniversityspringdata.service.EmployeeService;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._1SimpleMapping.org.softwareuniversityspringdata.service.dtos.EmployeeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}
