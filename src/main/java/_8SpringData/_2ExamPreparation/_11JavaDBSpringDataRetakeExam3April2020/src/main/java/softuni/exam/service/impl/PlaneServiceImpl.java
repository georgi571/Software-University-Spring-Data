package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.PlaneSeedDTO;
import softuni.exam.models.dto.xml.PlaneSeedRootDTO;
import softuni.exam.models.entities.Plane;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class PlaneServiceImpl implements PlaneService {

    private final String PATH_INPUT = "src/main/resources/files/xml/planes.xml";

    private final PlaneRepository planeRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public PlaneServiceImpl(PlaneRepository planeRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.planeRepository = planeRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return this.planeRepository.count() > 0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importPlanes() throws JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        PlaneSeedRootDTO planeSeedRootDTO = this.xmlParser.parse(PlaneSeedRootDTO.class, PATH_INPUT);
        for (PlaneSeedDTO planeSeedDTO : planeSeedRootDTO.getPlaneSeedDTOS()) {
            Optional<Plane> planeOptional = this.planeRepository.findByRegisterNumber(planeSeedDTO.getRegisterNumber());
            if (!this.validationUtil.isValid(planeSeedDTO) || planeOptional.isPresent()) {
                stringBuilder.append("Invalid Plane").append(System.lineSeparator());
                continue;
            }
            Plane plane = this.modelMapper.map(planeSeedDTO, Plane.class);
            this.planeRepository.saveAndFlush(plane);
            stringBuilder.append(String.format("Successfully imported Plane %s", plane.getRegisterNumber())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
