package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.TownSeedDTO;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class TownServiceImpl implements TownService {

    private final String PATH_INPUT = "src/main/resources/files/json/towns.json";

    private final TownRepository townRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public TownServiceImpl(TownRepository townRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importTowns() throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();

        TownSeedDTO[] townSeedDTOS = this.gson.fromJson(new FileReader(PATH_INPUT), TownSeedDTO[].class);
        for (TownSeedDTO townSeedDTO : townSeedDTOS) {
            Optional<Town> townOptional = this.townRepository.findByName(townSeedDTO.getName());
            if (!this.validationUtil.isValid(townSeedDTO) || townOptional.isPresent()) {
                stringBuilder.append("Invalid Town").append(System.lineSeparator());
                continue;
            }
            Town town = this.modelMapper.map(townSeedDTO, Town.class);
            this.townRepository.saveAndFlush(town);
            stringBuilder.append(String.format("Successfully imported Town %s - %d", town.getName(), town.getPopulation())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
