package _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.impl;

import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.imports.CustomerSeedDTO;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.entities.Car;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.entities.Customer;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.entities.Part;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.repositories.CustomerRepository;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.CustomerService;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CustomerOrder.CarOrderedDTO;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CustomerOrder.CustomerOrderedDTO;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CustomerOrder.SalesOrderedDTO;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CustomerTotalSales.CustomerTotalSaleDTO;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final String FILE_INPUT_PATH = "src/main/resources/json/imports/customers.json";
    private static final String FILE_OUTPUT_PATH_ORDERED_CUSTOMERS = "src/main/resources/json/exports/ordered-customers.json";
        private static final String FILE_OUTPUT_PATH_CUSTOMERS_TOTAL_SALES = "src/main/resources/json/exports/customers-total-sales.json";
    private final CustomerRepository customerRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedCustomers() throws FileNotFoundException {
        if (this.customerRepository.count() == 0) {
            CustomerSeedDTO[] customerSeedDTOs = this.gson.fromJson(new FileReader(FILE_INPUT_PATH), CustomerSeedDTO[].class);
            for (CustomerSeedDTO customerSeedDTO : customerSeedDTOs) {
                if (!this.validationUtil.isValid(customerSeedDTO)) {
                    this.validationUtil.getViolations(customerSeedDTO)
                            .forEach(validationUtil -> System.out.printf("%s%n", validationUtil.getMessage()));
                    continue;
                }
                Customer customer = this.modelMapper.map(customerSeedDTO, Customer.class);
                this.customerRepository.saveAndFlush(customer);
            }
        }
    }

    @Override
    public List<CustomerOrderedDTO> getAllCustomers() {
        return this.customerRepository.findAllByOrderByBirthDateAsc()
                .stream()
                .map(customer -> {
                    CustomerOrderedDTO dto = this.modelMapper.map(customer, CustomerOrderedDTO.class);

                    Set<SalesOrderedDTO> salesOrderedDTOs = customer.getSales().stream()
                            .map(sale -> {
                                SalesOrderedDTO saleDto = this.modelMapper.map(sale, SalesOrderedDTO.class);
                                Car car = sale.getCar();
                                CarOrderedDTO carOrderedDTO = this.modelMapper.map(car, CarOrderedDTO.class);
                                saleDto.setCarOrderedDTO(carOrderedDTO);
                                saleDto.setPrice(sale.getCar().getParts().stream().map(Part::getPrice).reduce(BigDecimal::add).get());
                                saleDto.setPriceWithDiscount(saleDto.getPrice().multiply(BigDecimal.valueOf(sale.getDiscount())).setScale(2, RoundingMode.HALF_UP));
                                return saleDto;
                            })
                            .collect(Collectors.toSet());

                    dto.setSale(salesOrderedDTOs);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void exportOrderedCustomers() throws IOException {
        String customerOrderedDTO = this.gson.toJson(this.getAllCustomers());
        FileWriter writer = new FileWriter(FILE_OUTPUT_PATH_ORDERED_CUSTOMERS);
        writer.write(customerOrderedDTO);
        writer.flush();
    }

    @Override
    public List<CustomerTotalSaleDTO> getAllCustomersWithBoughtCars() {
        return this.customerRepository.findAllByWithBoughtCars()
                .stream()
                .map(customer -> {
                    CustomerTotalSaleDTO dto = this.modelMapper.map(customer, CustomerTotalSaleDTO.class);
                    dto.setFullName(customer.getName());
                    dto.setBoughtCars(customer.getSales().size());
                    double spendMoney = customer.getSales()
                            .stream()
                            .mapToDouble(sale -> sale.getCar()
                                    .getParts()
                                    .stream()
                                    .mapToDouble(parts -> parts.getPrice()
                                            .doubleValue()).sum() * sale.getDiscount())
                            .sum();
                    dto.setSpendMoney(BigDecimal.valueOf(spendMoney).setScale(2, RoundingMode.HALF_UP));
                    return dto;
                })
                .sorted(Comparator.comparing(CustomerTotalSaleDTO::getSpendMoney).reversed()
                        .thenComparing(Comparator.comparing(CustomerTotalSaleDTO::getBoughtCars).reversed()))
                .collect(Collectors.toList());
    }

    @Override
    public void exportCustomersWithBoughtCars() throws IOException {
        String customerTotalSaleDTO = this.gson.toJson(this.getAllCustomersWithBoughtCars());
        FileWriter writer = new FileWriter(FILE_OUTPUT_PATH_CUSTOMERS_TOTAL_SALES);
        writer.write(customerTotalSaleDTO);
        writer.flush();
    }
}
