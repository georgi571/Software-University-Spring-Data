package _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.impl;

import _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.CustomerService;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CustomerOrder.CustomerOrderedDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CustomerOrder.CustomerOrderedRootDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CustomerTotalSales.CustomerTotalSaleDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CustomerTotalSales.CustomerTotalSalesRootDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.imports.CustomerSeedDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.imports.CustomerSeedRootDTO;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.entities.Customer;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.repositories.CustomerRepository;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.util.ValidationUtil;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.util.XmlParser;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final String FILE_INPUT_PATH = "src/main/resources/xml/imports/customers.xml";
    private static final String FILE_OUTPUT_PATH_ORDERED_CUSTOMERS = "src/main/resources/xml/exports/ordered-customers.xml";
    private static final String FILE_OUTPUT_PATH_CUSTOMERS_TOTAL_SALES = "src/main/resources/xml/exports/customers-total-sales.xml";
    private final CustomerRepository customerRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCustomers() throws JAXBException {
        if (this.customerRepository.count() == 0) {
            CustomerSeedRootDTO customerSeedRootDTO = xmlParser.parse(CustomerSeedRootDTO.class, FILE_INPUT_PATH);
            for (CustomerSeedDTO customerSeedDTO : customerSeedRootDTO.getCustomerSeedDTOList()) {
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
    public void exportOrderedCustomers() throws JAXBException {
        List<CustomerOrderedDTO> customerOrderedDTOList = this.customerRepository.findAllByOrderByBirthDateAscIsYoungDriverAsc()
                .stream()
                .map(customer -> this.modelMapper.map(customer, CustomerOrderedDTO.class))
                .collect(Collectors.toList());
        CustomerOrderedRootDTO customerOrderedRootDTO = new CustomerOrderedRootDTO();
        customerOrderedRootDTO.setCustomerOrderedDTOList(customerOrderedDTOList);

        this.xmlParser.exportToFile(CustomerOrderedRootDTO.class, customerOrderedRootDTO, FILE_OUTPUT_PATH_ORDERED_CUSTOMERS);
    }

    @Override
    public void exportCustomersWithBoughtCars() throws JAXBException {
        List<CustomerTotalSaleDTO> customerTotalSaleDTOList = this.customerRepository.findAllByWithBoughtCars()
                .stream()
                .map(customer -> {
                    CustomerTotalSaleDTO customerTotalSaleDTO = new CustomerTotalSaleDTO();
                    customerTotalSaleDTO.setFullName(customer.getName());
                    customerTotalSaleDTO.setBoughtCars(customer.getSales().size());
                    double spendMoney = customer.getSales()
                            .stream()
                            .mapToDouble(sale -> sale.getCar()
                                    .getParts()
                                    .stream()
                                    .mapToDouble(parts -> parts.getPrice()
                                            .doubleValue()).sum() * sale.getDiscount())
                            .sum();
                    customerTotalSaleDTO.setSpendMoney(BigDecimal.valueOf(spendMoney).setScale(2, RoundingMode.HALF_UP));

                    return customerTotalSaleDTO;
                })
                .sorted(Comparator.comparing(CustomerTotalSaleDTO::getBoughtCars).reversed()
                        .thenComparing(Comparator.comparing(CustomerTotalSaleDTO::getSpendMoney).reversed()))
                .collect(Collectors.toList());

        CustomerTotalSalesRootDTO customerTotalSalesRootDTO = new CustomerTotalSalesRootDTO();
        customerTotalSalesRootDTO.setCustomerTotalSaleDTOList(customerTotalSaleDTOList);

        this.xmlParser.exportToFile(CustomerTotalSalesRootDTO.class, customerTotalSalesRootDTO, FILE_OUTPUT_PATH_CUSTOMERS_TOTAL_SALES);
    }
}
