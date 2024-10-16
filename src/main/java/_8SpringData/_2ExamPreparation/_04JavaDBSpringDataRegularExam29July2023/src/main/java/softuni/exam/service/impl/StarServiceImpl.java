package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.StarSeedDTO;
import softuni.exam.models.entity.Constellation;
import softuni.exam.models.entity.Star;
import softuni.exam.models.entity.StarType;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StarServiceImpl implements StarService {

    private final String PATH_INPUT = "src/main/resources/files/json/stars.json";

    private final StarRepository starRepository;
    private final ConstellationRepository constellationRepository;

    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public StarServiceImpl(StarRepository starRepository, ConstellationRepository constellationRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.starRepository = starRepository;
        this.constellationRepository = constellationRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.starRepository.count() > 0;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importStars() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        StarSeedDTO[] starSeedDTOS = this.gson.fromJson(new FileReader(PATH_INPUT), StarSeedDTO[].class);
        for (StarSeedDTO starSeedDTO : starSeedDTOS) {
            Optional<Star> starOptional = this.starRepository.findByName(starSeedDTO.getName());
            if (!this.validationUtil.isValid(starSeedDTO) || starOptional.isPresent()) {
                stringBuilder.append("Invalid star").append(System.lineSeparator());
                continue;
            }
            Star star = this.modelMapper.map(starSeedDTO, Star.class);
            StarType starType = StarType.valueOf(starSeedDTO.getStarType());
            star.setStarType(starType);
            Constellation constellation = this.constellationRepository.findById(starSeedDTO.getConstellation()).get();
            star.setConstellation(constellation);
            this.starRepository.saveAndFlush(star);
            stringBuilder.append(String.format("Successfully imported star %s - %.2f light years", star.getName(), star.getLightYears())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }

    @Override
    public String exportStars() {
        return this.starRepository.findAllByStarTypeAndObservedIsNullOrderByLightYearsAsc(StarType.RED_GIANT)
                .stream()
                .map(star -> String.format("Star: %s\n" +
                                "   *Distance: %.2f light years\n" +
                                "   **Description: %s\n" +
                                "   ***Constellation: %s\n",
                        star.getName(),
                        star.getLightYears(),
                        star.getDescription(),
                        star.getConstellation().getName()))
                .collect(Collectors.joining());
    }
}
