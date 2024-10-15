package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.VolcanologistSeedDTO;
import softuni.exam.models.dto.xml.VolcanologistSeedRootDTO;
import softuni.exam.models.entity.Volcano;
import softuni.exam.models.entity.Volcanologist;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.repository.VolcanologistRepository;
import softuni.exam.service.VolcanoService;
import softuni.exam.service.VolcanologistService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class VolcanologistServiceImpl implements VolcanologistService {

    private final String PATH_INPUT = "src/main/resources/files/xml/volcanologists.xml";

    private final VolcanologistRepository volcanologistRepository;
    private final VolcanoService volcanoService;
    private final VolcanoRepository volcanoRepository;

    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public VolcanologistServiceImpl(VolcanologistRepository volcanologistRepository, VolcanoService volcanoService, VolcanoRepository volcanoRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.volcanologistRepository = volcanologistRepository;
        this.volcanoService = volcanoService;
        this.volcanoRepository = volcanoRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.volcanologistRepository.count() > 0;
    }

    @Override
    public String readVolcanologistsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importVolcanologists() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        VolcanologistSeedRootDTO volcanologistSeedRootDTO = this.xmlParser.fromFile(PATH_INPUT, VolcanologistSeedRootDTO.class);
        for (VolcanologistSeedDTO volcanologistSeedDTO : volcanologistSeedRootDTO.getVolcanologistSeedDTOList()) {
            Optional<Volcanologist> volcanologistOptional = this.volcanologistRepository.findByFirstNameAndLastName(volcanologistSeedDTO.getFirstName(), volcanologistSeedDTO.getLastName());
            Optional<Volcano> volcano = this.volcanoRepository.findById(volcanologistSeedDTO.getExploringVolcanoId());
            if (!this.validationUtil.isValid(volcanologistSeedDTO) || volcanologistOptional.isPresent() || volcano.isEmpty()) {
                stringBuilder.append("Invalid volcanologist").append(System.lineSeparator());
                continue;
            }
            Volcanologist volcanologist = this.modelMapper.map(volcanologistSeedDTO, Volcanologist.class);
            volcanologist.setVolcano(volcano.get());
            this.volcanologistRepository.saveAndFlush(volcanologist);
            stringBuilder.append(String.format("Successfully imported volcanologist %s %s", volcanologist.getFirstName(), volcanologist.getLastName())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}