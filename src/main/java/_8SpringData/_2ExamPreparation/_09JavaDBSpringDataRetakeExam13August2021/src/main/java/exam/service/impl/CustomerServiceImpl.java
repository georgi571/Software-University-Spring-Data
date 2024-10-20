package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.json.CustomerSeedDTO;
import exam.model.entity.Customer;
import exam.model.entity.Town;
import exam.repository.CustomerRepository;
import exam.repository.TownRepository;
import exam.service.CustomerService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final String PATH_INPUT = "src/main/resources/files/json/customers.json";

    private final CustomerRepository customerRepository;
    private final TownRepository townRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public CustomerServiceImpl(CustomerRepository customerRepository, TownRepository townRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.customerRepository = customerRepository;
        this.townRepository = townRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importCustomers() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        CustomerSeedDTO[] customerSeedDTOS = this.gson.fromJson(new FileReader(PATH_INPUT), CustomerSeedDTO[].class);
        for (CustomerSeedDTO customerSeedDTO : customerSeedDTOS) {
            Optional<Customer> customerOptional = this.customerRepository.findByEmail(customerSeedDTO.getEmail());
            if (!this.validationUtil.isValid(customerSeedDTO) || customerOptional.isPresent()) {
                stringBuilder.append("Invalid customer").append(System.lineSeparator());
                continue;
            }
            Customer customer = this.modelMapper.map(customerSeedDTO, Customer.class);
            Town town = this.townRepository.findByName(customerSeedDTO.getTown().getName()).get();
            customer.setTown(town);
            this.customerRepository.saveAndFlush(customer);
            stringBuilder.append(String.format("Successfully imported Customer %s %s - %s", customer.getFirstName(), customer.getLastName(), customer.getEmail())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
