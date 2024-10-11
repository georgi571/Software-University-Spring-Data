package _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.impl;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.data.entities.Car;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.data.entities.Part;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.data.repositories.CarRepository;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.data.repositories.PartRepository;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.CarService;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.exports.CarAndParts.CarAndPartsDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.exports.CarAndParts.CarAndPartsRootDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.exports.CarAndParts.PartDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.exports.CarAndParts.PartRootDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.exports.CarMake.CarMakeDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.exports.CarMake.CarMakeRootDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.imports.CarSeedDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.imports.CarSeedRootDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.util.ValidationUtil;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.util.XmlParser;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private static final String FILE_INPUT_PATH = "src/main/resources/xml/imports/cars.xml";
    private static final String FILE_OUTPUT_PATH_CAR_MAKE = "src/main/resources/xml/exports/cars-make.xml";
    private static final String FILE_OUTPUT_PATH_CAR_AND_PARTS = "src/main/resources/xml/exports/cars-and-parts.xml";

    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCars() throws JAXBException {
        if (this.carRepository.count() == 0) {
            CarSeedRootDTO carSeedRootDTO = xmlParser.parse(CarSeedRootDTO.class, FILE_INPUT_PATH);
            for (CarSeedDTO carSeedDTO : carSeedRootDTO.getCarSeedDTOList()) {
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
    public void exportModelCars(String model) throws JAXBException {
        List<CarMakeDTO> carMakeDTOList = this.carRepository.findAllByMakeOrderByTravelledDistanceDesc(model)
                .stream()
                .map(car -> this.modelMapper.map(car, CarMakeDTO.class))
                .collect(Collectors.toList());

        CarMakeRootDTO carMakeRootDTO = new CarMakeRootDTO();
        carMakeRootDTO.setCarMakeDTOList(carMakeDTOList);

        this.xmlParser.exportToFile(CarMakeRootDTO.class, carMakeRootDTO, FILE_OUTPUT_PATH_CAR_MAKE);
    }

    @Override
    public void exportCarAndParts() throws JAXBException {
        List<CarAndPartsDTO> carAndPartsDTOS = this.carRepository.findAll()
                .stream()
                .map(car -> {
                    CarAndPartsDTO carAndPartsDTO = this.modelMapper.map(car, CarAndPartsDTO.class);

                    PartRootDTO partRootDTO = new PartRootDTO();
                    List<PartDTO> partDTOList = car.getParts()
                            .stream()
                            .map(part -> this.modelMapper.map(part, PartDTO.class))
                            .collect(Collectors.toList());

                    partRootDTO.setPartDTOList(partDTOList);
                    carAndPartsDTO.setPartRootDTO(partRootDTO);
                    return carAndPartsDTO;
                })
                .collect(Collectors.toList());

        CarAndPartsRootDTO carAndPartsRootDTO = new CarAndPartsRootDTO();
        carAndPartsRootDTO.setCarAndPartsDTOList(carAndPartsDTOS);

        this.xmlParser.exportToFile(CarAndPartsRootDTO.class, carAndPartsRootDTO, FILE_OUTPUT_PATH_CAR_AND_PARTS);
    }

    private Set<Part> getRandomParts() {
        Set<Part> parts = new HashSet<>();
        int count = ThreadLocalRandom.current().nextInt(10, 21);
        for (int i = 0; i < count; i++) {
            parts.add(this.partRepository.findById(ThreadLocalRandom.current().nextLong(1, this.partRepository.count() + 1)).get());
        }

        return parts;
    }
}
