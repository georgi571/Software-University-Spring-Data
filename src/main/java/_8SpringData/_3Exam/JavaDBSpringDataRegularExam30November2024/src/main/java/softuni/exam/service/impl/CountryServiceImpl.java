package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.CountrySeedDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final String PATH_INPUT = "src/main/resources/files/json/countries.json";

    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public CountryServiceImpl(CountryRepository countryRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountryFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importCountries() throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();

        CountrySeedDTO[] countrySeedDTOS = this.gson.fromJson(new FileReader(PATH_INPUT), CountrySeedDTO[].class);
        for (CountrySeedDTO countrySeedDTO : countrySeedDTOS) {
            Optional<Country> countryOptional = this.countryRepository.findByName(countrySeedDTO.getName());
            if (!this.validationUtil.isValid(countrySeedDTO) || countryOptional.isPresent()) {
                stringBuilder.append("Invalid country").append(System.lineSeparator());
                continue;
            }
            Country country = this.modelMapper.map(countrySeedDTO, Country.class);
            this.countryRepository.saveAndFlush(country);
            stringBuilder.append(String.format("Successfully imported country %s", country.getName())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
