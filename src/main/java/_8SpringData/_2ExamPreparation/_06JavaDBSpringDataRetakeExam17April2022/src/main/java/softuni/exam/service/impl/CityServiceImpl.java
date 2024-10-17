package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.CitySeedDTO;
import softuni.exam.models.entity.City;
import softuni.exam.repository.CityRepository;
import softuni.exam.service.CityService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    private final String PATH_INPUT = "src/main/resources/files/json/cities.json";

    private final CityRepository cityRepository;

    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public CityServiceImpl(CityRepository cityRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.cityRepository = cityRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importCities() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        CitySeedDTO[] citySeedDTOS = this.gson.fromJson(new FileReader(PATH_INPUT), CitySeedDTO[].class);
        for (CitySeedDTO citySeedDTO : citySeedDTOS) {
            Optional<City> cityOptional = this.cityRepository.findByCityName(citySeedDTO.getCityName());
            if (!this.validationUtil.isValid(citySeedDTO) || cityOptional.isPresent()) {
                stringBuilder.append("Invalid city").append(System.lineSeparator());
                continue;
            }
            City city = this.modelMapper.map(citySeedDTO, City.class);
            this.cityRepository.saveAndFlush(city);
            stringBuilder.append(String.format("Successfully imported city %s - %d", city.getCityName(), city.getPopulation())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
