package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.PictureSeedDTO;
import softuni.exam.models.enity.Car;
import softuni.exam.models.enity.Picture;
import softuni.exam.models.enity.Seller;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {

    private final String PATH_INPUT = "src/main/resources/files/json/pictures.json";

    private final PictureRepository pictureRepository;
    private final CarRepository carRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public PictureServiceImpl(PictureRepository pictureRepository, CarRepository carRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.pictureRepository = pictureRepository;
        this.carRepository = carRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        PictureSeedDTO[] pictureSeedDTOS = this.gson.fromJson(new FileReader(PATH_INPUT), PictureSeedDTO[].class);
        for (PictureSeedDTO pictureSeedDTO : pictureSeedDTOS) {
            Optional<Picture> pictureOptional = this.pictureRepository.findByName(pictureSeedDTO.getName());
            if (!this.validationUtil.isValid(pictureSeedDTO) || pictureOptional.isPresent()) {
                stringBuilder.append("Invalid picture").append(System.lineSeparator());
                continue;
            }
            Picture picture = this.modelMapper.map(pictureSeedDTO, Picture.class);
            Car car = this.carRepository.findById(pictureSeedDTO.getCar()).get();
            car.getPictures().add(picture);
            picture.setCar(car);
            this.carRepository.saveAndFlush(car);
            this.pictureRepository.saveAndFlush(picture);
            stringBuilder.append(String.format("Successfully import picture - %s", picture.getName())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
