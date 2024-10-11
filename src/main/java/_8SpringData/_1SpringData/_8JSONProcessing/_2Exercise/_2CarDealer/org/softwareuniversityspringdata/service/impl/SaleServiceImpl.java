package _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.impl;

import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CarAndParts.CarDTO;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.imports.SaleSeedDTO;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.entities.Car;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.entities.Customer;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.entities.Part;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.entities.Sale;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.repositories.CarRepository;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.repositories.CustomerRepository;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.repositories.SaleRepository;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.SaleService;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.SalesWithAppliedDiscount.SalesDiscountDTO;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.util.ValidationUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {
    private final List<Double> discounts = List.of(1.0, 0.95, 0.9, 0.85, 0.8, 0.7, 0.6, 0.5);
    private static final String FILE_OUTPUT_PATH = "src/main/resources/json/exports/sales-discounts.json";

    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.gson = gson;
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
    public List<SalesDiscountDTO> getAllSalesInfo() {
        return this.saleRepository.findAll()
                .stream()
                .map(sale -> {
                    SalesDiscountDTO dto = this.modelMapper.map(sale, SalesDiscountDTO.class);
                    CarDTO carDTO = this.modelMapper.map(sale.getCar(), CarDTO.class);
                    dto.setCarDTO(carDTO);
                    dto.setDiscount(BigDecimal.valueOf(1 - sale.getDiscount()).setScale(2, RoundingMode.HALF_UP).doubleValue());
                    dto.setPrice(sale.getCar().getParts().stream()
                            .map(Part::getPrice).reduce(BigDecimal::add).get());
                    dto.setPriceWithDiscount(dto.getPrice().multiply(BigDecimal.valueOf(sale.getDiscount())).setScale(2, RoundingMode.HALF_UP));
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public void exportSales() throws IOException {
        String salesDiscountDTO = this.gson.toJson(this.getAllSalesInfo());
        FileWriter writer = new FileWriter(FILE_OUTPUT_PATH);
        writer.write(salesDiscountDTO);
        writer.flush();
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
