package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.domain.dtos.json.EmployeeCardSeedDTO;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.service.EmployeeCardService;
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
public class EmployeeCardServiceImpl implements EmployeeCardService {

    private final String PATH_INPUT = "src/main/resources/files/employee-cards.json";

    private final EmployeeCardRepository employeeCardRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;


    public EmployeeCardServiceImpl(EmployeeCardRepository employeeCardRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil) {
        this.employeeCardRepository = employeeCardRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean employeeCardsAreImported() {
        return this.employeeCardRepository.count() > 0;
    }

    @Override
    public String readEmployeeCardsJsonFile() throws IOException {
        return this.fileUtil.readFile(PATH_INPUT);
    }

    @Override
    public String importEmployeeCards(String employeeCardsFileContent) throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();

        EmployeeCardSeedDTO[] employeeCardSeedDTOS = this.gson.fromJson(new FileReader(PATH_INPUT), EmployeeCardSeedDTO[].class);
        for (EmployeeCardSeedDTO employeeCardSeedDTO : employeeCardSeedDTOS) {
            Optional<EmployeeCard> employeeCardOptional = this.employeeCardRepository.findByNumber(employeeCardSeedDTO.getNumber());
            if (!this.validationUtil.isValid(employeeCardSeedDTO) || employeeCardOptional.isPresent()) {
                stringBuilder.append("Error: Invalid data.").append(System.lineSeparator());
                continue;
            }
            EmployeeCard employeeCard = this.modelMapper.map(employeeCardSeedDTO, EmployeeCard.class);
            this.employeeCardRepository.saveAndFlush(employeeCard);
            stringBuilder.append(String.format("Successfully imported Employee Card %s.", employeeCard.getNumber())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
