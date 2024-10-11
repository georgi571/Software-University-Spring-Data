package _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.impl;

import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CarAndParts.CarAndPartsDTO;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CarAndParts.CarDTO;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CarAndParts.PartDTO;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CarMake.CarMakeDTO;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.imports.CarSeedDTO;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.entities.Car;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.entities.Part;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.repositories.CarRepository;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.repositories.PartRepository;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.CarService;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private static final String FILE_INPUT_PATH = "src/main/resources/json/imports/cars.json";
    private static final String FILE_OUTPUT_PATH_CAR_MAKE = "src/main/resources/json/exports/cars-make.json";
    private static final String FILE_OUTPUT_PATH_CAR_AND_PARTS = "src/main/resources/json/exports/cars-and-parts.json";

    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedCars() throws FileNotFoundException {
        if (this.carRepository.count() == 0) {
            CarSeedDTO[] carSeedDTOs = this.gson.fromJson(new FileReader(FILE_INPUT_PATH), CarSeedDTO[].class);
            for (CarSeedDTO carSeedDTO : carSeedDTOs) {
                if (!this.validationUtil.isValid(carSeedDTO)) {
                    this.validationUtil.getViolations(carSeedDTO)
                            .forEach(validationUtil -> System.out.printf("%s%n", validationUtil.getMessage()));
                    continue;
                }
                Car car = this.modelMapper.map(carSeedDTO, Car.class);
                car.setParts(getRandomParts());
                this.carRepository.saveAndFlush(car);
            }
        }
    }

    @Override
    public List<CarMakeDTO> getAllCarsByMake(String make) {
        return this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(make)
                .stream()
                .map(customer -> this.modelMapper.map(customer, CarMakeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void exportModelCars(String model) throws IOException {
        String carMakeDTO = this.gson.toJson(this.getAllCarsByMake(model));
        FileWriter writer = new FileWriter(FILE_OUTPUT_PATH_CAR_MAKE);
        writer.write(carMakeDTO);
        writer.flush();
    }

    public List<CarAndPartsDTO> getAllCarsAndParts() {
        return this.carRepository.findAll()
                .stream()
                .map(car -> {
                    CarAndPartsDTO dto = new CarAndPartsDTO();
                    CarDTO carDTO = this.modelMapper.map(car, CarDTO.class);
                    List<PartDTO> partDTOs = car.getParts()
                            .stream()
                            .map(part -> this.modelMapper.map(part, PartDTO.class))
                            .collect(Collectors.toList());
                    dto.setCar(carDTO);
                    dto.setParts(partDTOs);
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public void exportCarAndParts() throws IOException {
        String carAndPartsDTO = this.gson.toJson(this.getAllCarsAndParts());
        FileWriter writer = new FileWriter(FILE_OUTPUT_PATH_CAR_AND_PARTS);
        writer.write(carAndPartsDTO);
        writer.flush();
    }

    private Set<Part> getRandomParts() {
        Set<Part> parts = new HashSet<>();
        int count = ThreadLocalRandom.current().nextInt(3, 6);
        for (int i = 0; i < count; i++) {
            parts.add(this.partRepository.findById(ThreadLocalRandom.current().nextLong(1, this.partRepository.count() + 1)).get());
        }

        return parts;
    }
}
