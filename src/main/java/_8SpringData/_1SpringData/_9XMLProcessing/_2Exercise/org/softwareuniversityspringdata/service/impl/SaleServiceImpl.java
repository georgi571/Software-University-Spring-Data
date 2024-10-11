package _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.impl;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.data.entities.Car;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.data.entities.Customer;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.data.entities.Part;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.data.entities.Sale;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.data.repositories.CarRepository;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.data.repositories.CustomerRepository;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.data.repositories.SaleRepository;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.SaleService;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.exports.SalesWithAppliedDiscount.CarDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.exports.SalesWithAppliedDiscount.SalesDiscountDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.exports.SalesWithAppliedDiscount.SalesDiscountRootDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.imports.SaleSeedDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.util.ValidationUtil;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.util.XmlParser;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {
    private final List<Double> discounts = List.of(1.0, 0.95, 0.9, 0.85, 0.8, 0.7, 0.6, 0.5);
    private static final String FILE_OUTPUT_PATH = "src/main/resources/xml/exports/sales-discounts.xml";

    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedSales(){
        if (this.saleRepository.count() == 0) {
            for (int i = 0; i < 50; i++) {
                SaleSeedDTO saleSeedDTO = new SaleSeedDTO();
                Sale sale = this.modelMapper.map(saleSeedDTO, Sale.class);
                sale.setCar(getRandomCar());
                sale.setCustomer(getRandomCustomer());
                sale.setDiscount(getRandomDiscount());
                try {
                    this.saleRepository.saveAndFlush(sale);
                } catch (DataIntegrityViolationException e) {
                    System.out.println("A sale already exists for this car and customer.");
                }
            }
        }
    }

    @Override
    public void exportSales() throws JAXBException {
        List<SalesDiscountDTO> salesDiscountDTOList = this.saleRepository.findAll()
                .stream()
                .map(sale -> {
                    SalesDiscountDTO salesDiscountDTO = new SalesDiscountDTO();
                    CarDTO carDTO = this.modelMapper.map(sale, CarDTO.class);

                    salesDiscountDTO.setCarDTO(carDTO);
                    salesDiscountDTO.setCustomerName(sale.getCustomer().getName());
                    salesDiscountDTO.setDiscount(BigDecimal.valueOf(1 - sale.getDiscount()).setScale(2, RoundingMode.HALF_UP).doubleValue());
                    salesDiscountDTO.setPrice(sale.getCar().getParts().stream()
                            .map(Part::getPrice).reduce(BigDecimal::add).get());
                    salesDiscountDTO.setPriceWithDiscount(salesDiscountDTO.getPrice().multiply(BigDecimal.valueOf(sale.getDiscount())));
                    return salesDiscountDTO;
                }).collect(Collectors.toList());

        SalesDiscountRootDTO salesDiscountRootDTO = new SalesDiscountRootDTO();
        salesDiscountRootDTO.setSalesDiscountDTOList(salesDiscountDTOList);

        this.xmlParser.exportToFile(SalesDiscountRootDTO.class, salesDiscountRootDTO, FILE_OUTPUT_PATH);
    }

    private double getRandomDiscount() {
        return discounts.get(ThreadLocalRandom.current().nextInt(1, discounts.size()));
    }

    private Customer getRandomCustomer() {
        return this.customerRepository.findById(
                ThreadLocalRandom.current().nextLong(1, this.customerRepository.count() + 1)
        ).get();
    }

    private Car getRandomCar() {
        return this.carRepository.findById(
                ThreadLocalRandom.current().nextLong(1, this.carRepository.count() + 1)
        ).get();
    }
}
