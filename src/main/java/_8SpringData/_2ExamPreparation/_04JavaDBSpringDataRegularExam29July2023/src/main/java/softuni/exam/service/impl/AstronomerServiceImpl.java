package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.AstronomerSeedDTO;
import softuni.exam.models.dto.xml.AstronomerSeedRootDTO;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.AstronomerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class AstronomerServiceImpl implements AstronomerService {

    private final String PATH_INPUT = "src/main/resources/files/xml/astronomers.xml";

    private final AstronomerRepository astronomerRepository;
    private final StarRepository starRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public AstronomerServiceImpl(AstronomerRepository astronomerRepository, StarRepository starRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.astronomerRepository = astronomerRepository;
        this.starRepository = starRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.astronomerRepository.count() > 0;
    }

    @Override
    public String readAstronomersFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importAstronomers() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        AstronomerSeedRootDTO astronomerSeedRootDTO = this.xmlParser.parse(AstronomerSeedRootDTO.class, PATH_INPUT);
        for (AstronomerSeedDTO astronomerSeedDTO : astronomerSeedRootDTO.getAstronomerSeedDTOList()) {
            Optional<Astronomer> astronomerOptional = this.astronomerRepository.findByFirstNameAndLastName(astronomerSeedDTO.getFirstName(), astronomerSeedDTO.getLastName());
            Optional<Star> starOptional = this.starRepository.findById(astronomerSeedDTO.getStar());
            if (!this.validationUtil.isValid(astronomerSeedDTO) || astronomerOptional.isPresent() || starOptional.isEmpty()) {
                stringBuilder.append("Invalid astronomer").append(System.lineSeparator());
                continue;
            }
            Astronomer astronomer = this.modelMapper.map(astronomerSeedDTO, Astronomer.class);
            astronomer.setStar(starOptional.get());
            this.astronomerRepository.saveAndFlush(astronomer);
            stringBuilder.append(String.format("Successfully imported astronomer %s %s - %.2f", astronomer.getFirstName(), astronomer.getLastName(), astronomer.getAverageObservationHours())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
