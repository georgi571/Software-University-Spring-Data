package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.domain.dtos.json.TownSeedDTO;
import hiberspring.domain.entities.Town;
import hiberspring.repository.TownRepository;
import hiberspring.service.TownService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@Service
public class TownServiceImpl implements TownService {

    private final String PATH_INPUT = "src/main/resources/files/towns.json";

    private final TownRepository townRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;


    public TownServiceImpl(TownRepository townRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean townsAreImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return this.fileUtil.readFile(PATH_INPUT);
    }

    @Override
    public String importTowns(String townsFileContent) throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();

        TownSeedDTO[] townSeedDTOS = this.gson.fromJson(new FileReader(PATH_INPUT), TownSeedDTO[].class);
        for (TownSeedDTO townSeedDTO : townSeedDTOS) {
            Optional<Town> townOptional = this.townRepository.findByName(townSeedDTO.getName());
            if (!this.validationUtil.isValid(townSeedDTO) || townOptional.isPresent()) {
                stringBuilder.append("Error: Invalid data.").append(System.lineSeparator());
                continue;
            }
            Town town = this.modelMapper.map(townSeedDTO, Town.class);
            this.townRepository.saveAndFlush(town);
            stringBuilder.append(String.format("Successfully imported Town %s.", town.getName())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
