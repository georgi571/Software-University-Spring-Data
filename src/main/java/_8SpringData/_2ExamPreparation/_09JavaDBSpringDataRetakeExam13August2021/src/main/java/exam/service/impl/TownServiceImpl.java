package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.xml.TownSeedDTO;
import exam.model.dto.xml.TownSeedRootDTO;
import exam.model.entity.Town;
import exam.repository.TownRepository;
import exam.service.TownService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class TownServiceImpl implements TownService {

    private final String PATH_INPUT = "src/main/resources/files/xml/towns.xml";

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
    public String importTowns() throws JAXBException, FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();

        TownSeedRootDTO townSeedRootDTO = this.xmlParser.parse(TownSeedRootDTO.class, PATH_INPUT);
        for (TownSeedDTO townSeedDTO : townSeedRootDTO.getTownSeedDTOS()) {
            Optional<Town> townOptional = this.townRepository.findByName(townSeedDTO.getName());
            if (!this.validationUtil.isValid(townSeedDTO) || townOptional.isPresent()) {
                stringBuilder.append("Invalid town").append(System.lineSeparator());
                continue;
            }
            Town town = this.modelMapper.map(townSeedDTO, Town.class);
            this.townRepository.saveAndFlush(town);
            stringBuilder.append(String.format("Successfully imported Town %s", town.getName())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
