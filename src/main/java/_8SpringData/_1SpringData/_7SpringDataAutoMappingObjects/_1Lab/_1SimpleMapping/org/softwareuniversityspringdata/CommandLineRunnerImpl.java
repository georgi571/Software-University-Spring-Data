package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._1SimpleMapping.org.softwareuniversityspringdata;

import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._1SimpleMapping.org.softwareuniversityspringdata.data.entities.Address;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._1SimpleMapping.org.softwareuniversityspringdata.data.entities.Employee;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._1SimpleMapping.org.softwareuniversityspringdata.service.AddressService;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._1SimpleMapping.org.softwareuniversityspringdata.service.EmployeeService;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._1SimpleMapping.org.softwareuniversityspringdata.service.dtos.EmployeeDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final EmployeeService employeeService;
    private final AddressService addressService;

    public CommandLineRunnerImpl(EmployeeService employeeService, AddressService addressService) {
        this.employeeService = employeeService;
        this.addressService = addressService;
    }

    @Override
    public void run(String... args) throws Exception {
        String firstName = "Georgi";
        String lastName = "Kostadinov";
        double salary = 1000.00;
        LocalDate birthday = LocalDate.of(1993, 8, 14);
        String country = "Bulgaria";
        String city = "Blagoevgrad";
        String street = "Street123";

        Address address = new Address();
        String newAddress = this.addressService.createNewAddress(address);
        System.out.printf("%s%n", newAddress);

        Employee employee = new Employee(firstName, lastName, salary, birthday, address);
        String hireInfoFromEmployee = this.employeeService.hireNewEmployee(employee);
        System.out.printf("%s%n", hireInfoFromEmployee);

        EmployeeDTO employeeDTO = new EmployeeDTO(firstName, lastName, salary);
        String hireInfoFromDTO = this.employeeService.hireNewEmployeeByDTO(employeeDTO);
        System.out.printf("%s%n", hireInfoFromDTO);

        String employeeName = "Georgi";
        String infoAboutNewEmployee = this.employeeService.getInfoAboutNewEmployee(employeeName);
        System.out.printf("%s%n", infoAboutNewEmployee);
    }
}
