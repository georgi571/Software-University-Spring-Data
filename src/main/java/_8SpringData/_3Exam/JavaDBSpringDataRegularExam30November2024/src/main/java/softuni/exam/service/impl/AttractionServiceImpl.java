package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.AttractionSeedDTO;
import softuni.exam.models.entity.Attraction;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.AttractionRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.AttractionService;
import softuni.exam.util.ValidationUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttractionServiceImpl implements AttractionService {

    private final String PATH_INPUT = "src/main/resources/files/json/attractions.json";

    private final AttractionRepository attractionRepository;
    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public AttractionServiceImpl(AttractionRepository attractionRepository, CountryRepository countryRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.attractionRepository = attractionRepository;
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return this.attractionRepository.count() > 0;
    }

    @Override
    public String readAttractionsFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importAttractions() throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();

        AttractionSeedDTO[] attractionSeedDTOS = this.gson.fromJson(new FileReader(PATH_INPUT), AttractionSeedDTO[].class);
        for (AttractionSeedDTO attractionSeedDTO : attractionSeedDTOS) {
            Optional<Attraction> attractionOptional = this.attractionRepository.findByName(attractionSeedDTO.getName());
            if (!this.validationUtil.isValid(attractionSeedDTO) || attractionOptional.isPresent()) {
                stringBuilder.append("Invalid attraction").append(System.lineSeparator());
                continue;
            }
            Attraction attraction = this.modelMapper.map(attractionSeedDTO, Attraction.class);
            Country country = this.countryRepository.findById(attractionSeedDTO.getCountry()).get();
            attraction.setCountry(country);
            this.attractionRepository.saveAndFlush(attraction);
            stringBuilder.append(String.format("Successfully imported attraction %s", attraction.getName())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }

    @Override
    public String exportAttractions() {
        return this.attractionRepository.findAllByTypeOrTypeAndElevationGreaterThanEqual("historical site", "archaeological site", 300)
                .stream()
                .map(attraction -> String.format("Attraction with ID%d:\n" +
                                "***%s - %s at an altitude of %dm. somewhere in %s.\n",
                        attraction.getId(),
                        attraction.getName(),
                        attraction.getDescription(),
                        attraction.getElevation(),
                        attraction.getCountry().getName()))
                .collect(Collectors.joining());
    }
}
