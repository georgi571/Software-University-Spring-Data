package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.CarSeedDTO;
import softuni.exam.models.enity.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final String PATH_INPUT = "src/main/resources/files/json/cars.json";

    private final CarRepository carRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public CarServiceImpl(CarRepository carRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.carRepository = carRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importCars() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        CarSeedDTO[] carSeedDTOS = this.gson.fromJson(new FileReader(PATH_INPUT), CarSeedDTO[].class);
        for (CarSeedDTO carSeedDTO : carSeedDTOS) {
            Optional<Car> sellerOptional = this.carRepository.findByMakeAndModelAndKilometers(carSeedDTO.getMake(), carSeedDTO.getModel(), carSeedDTO.getKilometers());
            if (!this.validationUtil.isValid(carSeedDTO) || sellerOptional.isPresent()) {
                stringBuilder.append("Invalid car").append(System.lineSeparator());
                continue;
            }
            Car car = this.modelMapper.map(carSeedDTO, Car.class);
            this.carRepository.saveAndFlush(car);
            stringBuilder.append(String.format("Successfully imported car - %s - %s", car.getMake(), car.getModel())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        return this.carRepository.findAllOrderByPictureCountDescMakeAsc()
                .stream()
                .map(car -> String.format("Car make - %s, model - %s\n" +
                                "Kilometers - %d\n" +
                                "Registered on - %s\n" +
                                "Number of pictures - %d\n\n",
                        car.getMake(), car.getModel(),
                        car.getKilometers(),
                        car.getRegisteredOn(),
                        car.getPictures().size()))
                .collect(Collectors.joining());
    }
}
