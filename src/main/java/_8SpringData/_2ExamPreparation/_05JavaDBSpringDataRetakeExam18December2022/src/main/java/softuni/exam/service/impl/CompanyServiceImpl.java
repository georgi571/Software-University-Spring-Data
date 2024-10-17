package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.CompanySeedDTO;
import softuni.exam.models.dto.xml.CompanySeedRootDTO;
import softuni.exam.models.entity.Company;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CompanyRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CompanyService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final String PATH_INPUT = "src/main/resources/files/xml/companies.xml";

    private final CompanyRepository companyRepository;
    private final CountryRepository countryRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public CompanyServiceImpl(CompanyRepository companyRepository, CountryRepository countryRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.companyRepository = companyRepository;
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return this.companyRepository.count() > 0;
    }

    @Override
    public String readCompaniesFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importCompanies() throws JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        CompanySeedRootDTO companySeedRootDTO = this.xmlParser.parse(CompanySeedRootDTO.class, PATH_INPUT);
        for (CompanySeedDTO companySeedDTO : companySeedRootDTO.getCompanySeedDTOList()) {
            Optional<Company> companyOptional = this.companyRepository.findByName(companySeedDTO.getName());
            if (!this.validationUtil.isValid(companySeedDTO) || companyOptional.isPresent()) {
                stringBuilder.append("Invalid company").append(System.lineSeparator());
                continue;
            }
            Company company = this.modelMapper.map(companySeedDTO, Company.class);
            Country country = this.countryRepository.findById(companySeedDTO.getCountryId()).get();
            company.setCountry(country);
            this.companyRepository.saveAndFlush(company);
            stringBuilder.append(String.format("Successfully imported company %s - %d", company.getName(), company.getCountry().getId())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
