package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.PersonSeedDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.models.entity.Person;
import softuni.exam.models.entity.StatusType;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.PersonRepository;
import softuni.exam.service.PersonService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final String PATH_INPUT = "src/main/resources/files/json/people.json";

    private final PersonRepository personRepository;
    private final CountryRepository countryRepository;

    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public PersonServiceImpl(PersonRepository personRepository, CountryRepository countryRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.personRepository = personRepository;
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.personRepository.count() > 0;
    }

    @Override
    public String readPeopleFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importPeople() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        PersonSeedDTO[] personSeedDTOS = this.gson.fromJson(new FileReader(PATH_INPUT), PersonSeedDTO[].class);
        for (PersonSeedDTO personSeedDTO : personSeedDTOS) {
            Optional<Person> personOptional = this.personRepository.findByFirstNameOrEmailOrPhoneNumber(personSeedDTO.getFirstName(), personSeedDTO.getEmail(), personSeedDTO.getPhoneNumber());
            if (!this.validationUtil.isValid(personSeedDTO) || personOptional.isPresent()) {
                stringBuilder.append("Invalid person").append(System.lineSeparator());
                continue;
            }
            Person person = this.modelMapper.map(personSeedDTO, Person.class);
            StatusType statusType = StatusType.valueOf(personSeedDTO.getStatusType());
            person.setStatusType(statusType);
            Country country = this.countryRepository.findById(personSeedDTO.getCountry()).get();
            person.setCountry(country);
            this.personRepository.saveAndFlush(person);
            stringBuilder.append(String.format("Successfully imported person %s %s", person.getFirstName(), person.getLastName())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
