package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._3Projection.org.softwareuniversityspringdata;

import org.modelmapper.ModelMapper;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._3Projection.org.softwareuniversityspringdata.data.entities.Address;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._3Projection.org.softwareuniversityspringdata.data.entities.Employee;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._3Projection.org.softwareuniversityspringdata.service.AddressService;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._3Projection.org.softwareuniversityspringdata.service.EmployeeService;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._3Projection.org.softwareuniversityspringdata.service.dtos.EmployeeDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final EmployeeService employeeService;
    private final AddressService addressService;
    private final ModelMapper modelMapper;

    public CommandLineRunnerImpl(EmployeeService employeeService, AddressService addressService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.addressService = addressService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        Address address1 = createNewAddress("Bulgaria", "Blagoevgrad", "Street123");
        Address address2 = createNewAddress("Bulgaria", "Sofia", "Street234");
        Address address3 = createNewAddress("Bulgaria", "Plovdiv", "Street345");
        Address address4 = createNewAddress("Bulgaria", "Varna", "Street456");
        Address address5 = createNewAddress("Bulgaria", "Burgas", "Street567");
        Address address6 = createNewAddress("Bulgaria", "Sandanski", "Street678");
        Address address7 = createNewAddress("Bulgaria", "Pernik", "Street789");

        Employee manager1 = createNewEmployee("Steve", "Jobbsen", 6000.00, LocalDate.of(1989, 1, 1), address1);
        Employee manager2 = createNewEmployee("Carl", "Kormac", 6000.00, LocalDate.of(2000, 1, 1), address2);

        Employee employee1 = createNewEmployee("Stephen", "Bjorn", 4300.00, LocalDate.of(1987, 2, 1), address3);
        Employee employee2 = createNewEmployee("Kirilyc", "Lefi", 4400.00, LocalDate.of(1988, 2, 2), address4);
        Employee employee3 = createNewEmployee("Jurgen", "Straus", 1000.45, LocalDate.of(2000, 3, 1), address5);
        Employee employee4 = createNewEmployee("Moni", "Kozinac", 2030.99, LocalDate.of(2000, 3, 2), address6);
        Employee employee5 = createNewEmployee("Kopp", "Spidok", 2000.21, LocalDate.of(2000, 3, 3), address7);

        String infoForManager1 = this.employeeService.setEmployeesToManager(manager1 , List.of(employee1, employee2));
        System.out.printf("%s%n", infoForManager1);

        String infoForManager2 = this.employeeService.setEmployeesToManager(manager2 , List.of(employee3, employee4, employee5));
        System.out.printf("%s%n", infoForManager2);

        List<EmployeeDTO> employees = this.employeeService.findInfoForEmployeesBornBefore(LocalDate.of(1990, 1, 1));
        for (EmployeeDTO employee : employees) {
            System.out.printf("%s%n", employee.toString());
        }
    }

    private Address createNewAddress(String country, String city, String street) {
        Address address = new Address(country, city, street);
        String newAddress = this.addressService.createNewAddress(address);
        System.out.printf("%s%n", newAddress);

        return address;
    }

    private Employee createNewEmployee(String firstName, String lastName, double salary, LocalDate birthday, Address address) {
        Employee employee = new Employee(firstName, lastName, salary, birthday, false, address, null);
        String hireInfo = this.employeeService.hireNewEmployee(employee);
        System.out.printf("%s%n", hireInfo);

        return employee;
    }
}
