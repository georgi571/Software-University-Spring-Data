package softuni.exam.service.impl;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.PersonalDataSeedDTO;
import softuni.exam.models.dto.xml.PersonalDataSeedRootDTO;
import softuni.exam.models.entity.PersonalData;
import softuni.exam.repository.PersonalDataRepository;
import softuni.exam.service.PersonalDataService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class PersonalDataServiceImpl implements PersonalDataService {

    private final String PATH_INPUT = "src/main/resources/files/xml/personal_data.xml";

    private final PersonalDataRepository personalDataRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public PersonalDataServiceImpl(PersonalDataRepository personalDataRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.personalDataRepository = personalDataRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.personalDataRepository.count() > 0;
    }

    @Override
    public String readPersonalDataFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importPersonalData() throws JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        PersonalDataSeedRootDTO personalDataSeedRootDTO = this.xmlParser.parse(PersonalDataSeedRootDTO.class, PATH_INPUT);
        for (PersonalDataSeedDTO personalDataSeedDTO : personalDataSeedRootDTO.getPersonalDataSeedDTOs()) {
            Optional<PersonalData> personalDataOptional = this.personalDataRepository.findByCardNumber(personalDataSeedDTO.getCardNumber());
            if (!this.validationUtil.isValid(personalDataSeedDTO) || personalDataOptional.isPresent()) {
                stringBuilder.append("Invalid personal data").append(System.lineSeparator());
                continue;
            }
            PersonalData personalData = this.modelMapper.map(personalDataSeedDTO, PersonalData.class);
            this.personalDataRepository.saveAndFlush(personalData);
            stringBuilder.append(String.format("Successfully imported personal data for visitor with card number %s", personalData.getCardNumber())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
