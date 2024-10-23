package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.domain.dtos.xml.EmployeeSeedDTO;
import hiberspring.domain.dtos.xml.EmployeeSeedRootDTO;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.repository.EmployeeRepository;
import hiberspring.service.EmployeeService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final String PATH_INPUT = "src/main/resources/files/employees.xml";

    private final EmployeeRepository employeeRepository;
    private final EmployeeCardRepository employeeCardRepository;
    private final BranchRepository branchRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeCardRepository employeeCardRepository, BranchRepository branchRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil) {
        this.employeeRepository = employeeRepository;
        this.employeeCardRepository = employeeCardRepository;
        this.branchRepository = branchRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return this.fileUtil.readFile(PATH_INPUT);
    }

    @Override
    public String importEmployees() throws JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        EmployeeSeedRootDTO employeeSeedRootDTO = this.xmlParser.parseXml(EmployeeSeedRootDTO.class, PATH_INPUT);
        for (EmployeeSeedDTO employeeSeedDTO : employeeSeedRootDTO.getEmployeeSeedDTOS()) {
            Optional<EmployeeCard> employeeCardOptional = this.employeeCardRepository.findByNumber(employeeSeedDTO.getCard());
            Optional<Branch> branchOptional = this.branchRepository.findByName(employeeSeedDTO.getBranch());
            Optional<Employee> employeeOptional = this.employeeRepository.findByCard(employeeCardOptional);
            if (!this.validationUtil.isValid(employeeSeedDTO) || employeeOptional.isPresent() || employeeCardOptional.isEmpty() || branchOptional.isEmpty()) {
                stringBuilder.append("Error: Invalid data.").append(System.lineSeparator());
                continue;
            }
            Employee employee = this.modelMapper.map(employeeSeedDTO, Employee.class);
            employee.setBranch(branchOptional.get());
            employee.setCard(employeeCardOptional.get());
            this.employeeRepository.saveAndFlush(employee);
            stringBuilder.append(String.format("Successfully imported Employee %s %s.", employee.getFirstName(), employee.getLastName())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }

    @Override
    public String exportProductiveEmployees() {
        return this.employeeRepository.findEmployeesWithBranchesHavingProducts()
                .stream()
                .map(employee -> String.format("Name: %s %s\n" +
                                "Position: %s\n" +
                                "Card Number: %s\n" +
                                "-------------------------\n",
                        employee.getFirstName(), employee.getLastName(),
                        employee.getPosition(),
                        employee.getCard().getNumber()))
                .collect(Collectors.joining());
    }
}
