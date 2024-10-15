package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.VolcanoSeedDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.models.entity.Volcano;
import softuni.exam.models.enums.VolcanoType;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.service.CountryService;
import softuni.exam.service.VolcanoService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VolcanoServiceImpl implements VolcanoService {

    private final String PATH_INPUT = "src/main/resources/files/json/volcanoes.json";

    private final VolcanoRepository volcanoRepository;
    private final CountryService countryService;

    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public VolcanoServiceImpl(VolcanoRepository volcanoRepository, CountryService countryService, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.volcanoRepository = volcanoRepository;
        this.countryService = countryService;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.volcanoRepository.count() > 0;
    }

    @Override
    public String readVolcanoesFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importVolcanoes() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        VolcanoSeedDTO[] volcanoSeedDTOS = this.gson.fromJson(new FileReader(PATH_INPUT), VolcanoSeedDTO[].class);
        for (VolcanoSeedDTO volcanoSeedDTO : volcanoSeedDTOS) {
            Optional<Volcano> volcanoOptional = this.volcanoRepository.findByName(volcanoSeedDTO.getName());
            if (!this.validationUtil.isValid(volcanoSeedDTO) || volcanoOptional.isPresent()) {
                stringBuilder.append("Invalid volcano").append(System.lineSeparator());
                continue;
            }
            Volcano volcano = this.modelMapper.map(volcanoSeedDTO, Volcano.class);
            Country country = this.countryService.getCountryById(volcanoSeedDTO.getCountry()).get();
            volcano.setCountry(country);
            VolcanoType volcanoType = VolcanoType.valueOf(volcanoSeedDTO.getVolcanoType());
            volcano.setVolcanoType(volcanoType);
            this.volcanoRepository.saveAndFlush(volcano);
            stringBuilder.append(String.format("Successfully imported volcano %s of type %s", volcano.getName(), volcano.getVolcanoType())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }

    @Override
    public Volcano findVolcanoById(Long volcanoId) {
        return this.volcanoRepository.getById(volcanoId);
    }

    @Override
    public String exportVolcanoes() {
        return this.volcanoRepository.findAllByIsActiveAndElevationGreaterThanAndLastEruptionIsNotNullOrderByElevationDesc(3000)
                .stream()
                .map(volcano -> String.format("Volcano: %s\n" +
                                "   *Located in: %s\n" +
                                "   **Elevation: %d\n" +
                                "   ***Last eruption on: %s\n",
                        volcano.getName(),
                        volcano.getCountry().getName(),
                        volcano.getElevation(),
                        volcano.getLastEruption()))
                .collect(Collectors.joining());
    }
}