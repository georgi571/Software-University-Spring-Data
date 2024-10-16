package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.ConstellationSeedDTO;
import softuni.exam.models.entity.Constellation;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.service.ConstellationService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class ConstellationServiceImpl implements ConstellationService {

    private final String PATH_INPUT = "src/main/resources/files/json/constellations.json";

    private final ConstellationRepository constellationRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public ConstellationServiceImpl(ConstellationRepository constellationRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.constellationRepository = constellationRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.constellationRepository.count() > 0;
    }

    @Override
    public String readConstellationsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importConstellations() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        ConstellationSeedDTO[] constellationSeedDTOS = this.gson.fromJson(new FileReader(PATH_INPUT), ConstellationSeedDTO[].class);
        for (ConstellationSeedDTO constellationSeedDTO : constellationSeedDTOS) {
            Optional<Constellation> constellationOptional = this.constellationRepository.findByName(constellationSeedDTO.getName());
            if (!this.validationUtil.isValid(constellationSeedDTO) || constellationOptional.isPresent()) {
                stringBuilder.append("Invalid constellation").append(System.lineSeparator());
                continue;
            }
            Constellation constellation = this.modelMapper.map(constellationSeedDTO, Constellation.class);
            this.constellationRepository.saveAndFlush(constellation);
            stringBuilder.append(String.format("Successfully imported constellation %s - %s",constellation.getName(), constellation.getDescription())).append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}
