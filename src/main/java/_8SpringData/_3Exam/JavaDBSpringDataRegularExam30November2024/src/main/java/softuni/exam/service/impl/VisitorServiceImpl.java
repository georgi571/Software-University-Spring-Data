package softuni.exam.service.impl;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.VisitorSeedDTO;
import softuni.exam.models.dto.xml.VisitorSeedRootDTO;
import softuni.exam.models.entity.Attraction;
import softuni.exam.models.entity.Country;
import softuni.exam.models.entity.PersonalData;
import softuni.exam.models.entity.Visitor;
import softuni.exam.repository.AttractionRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.PersonalDataRepository;
import softuni.exam.repository.VisitorRepository;
import softuni.exam.service.VisitorService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class VisitorServiceImpl implements VisitorService {

    private final String PATH_INPUT = "src/main/resources/files/xml/visitors.xml";

    private final VisitorRepository visitorRepository;
    private final PersonalDataRepository personalDataRepository;
    private final CountryRepository countryRepository;
    private final AttractionRepository attractionRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public VisitorServiceImpl(VisitorRepository visitorRepository, PersonalDataRepository personalDataRepository, CountryRepository countryRepository, AttractionRepository attractionRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.visitorRepository = visitorRepository;
        this.personalDataRepository = personalDataRepository;
        this.countryRepository = countryRepository;
        this.attractionRepository = attractionRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.visitorRepository.count() > 0;
    }

    @Override
    public String readVisitorsFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importVisitors() throws JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        VisitorSeedRootDTO visitorSeedRootDTO = this.xmlParser.parse(VisitorSeedRootDTO.class, PATH_INPUT);
        for (VisitorSeedDTO visitorSeedDTO  : visitorSeedRootDTO.getVisitors()) {
            PersonalData personalData = this.personalDataRepository.findById(visitorSeedDTO.getPersonalDataId()).get();
            Optional<Visitor> visitorOptional = this.visitorRepository.findByFirstNameAndLastNameOrPersonalData(visitorSeedDTO.getFirstName(),
                    visitorSeedDTO.getLastName(), personalData);
            if (!this.validationUtil.isValid(visitorSeedDTO) || visitorOptional.isPresent()) {
                stringBuilder.append("Invalid visitor").append(System.lineSeparator());
                continue;
            }
            Visitor visitor = this.modelMapper.map(visitorSeedDTO, Visitor.class);
            Attraction attraction = this.attractionRepository.findById(visitorSeedDTO.getAttractionId()).get();
            Country country = this.countryRepository.findById(visitorSeedDTO.getCountryId()).get();
            visitor.setAttraction(attraction);
            visitor.setCountry(country);
            visitor.setPersonalData(personalData);
            this.visitorRepository.saveAndFlush(visitor);
            stringBuilder.append(String.format("Successfully imported visitor %s %s", visitor.getFirstName(),
                    visitor.getLastName())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
