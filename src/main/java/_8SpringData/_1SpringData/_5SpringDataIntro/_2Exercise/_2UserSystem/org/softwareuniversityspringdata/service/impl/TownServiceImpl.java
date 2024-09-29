package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.service.impl;

import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.Country;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.Town;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.repositories.CountryRepository;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.repositories.TownRepository;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.service.TownService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TownServiceImpl implements TownService {
    private static final String FILE_PATH = "src/main/resources/files/towns.txt";
    private final TownRepository townRepository;
    private final CountryRepository countryRepository;

    public TownServiceImpl(TownRepository townRepository, CountryRepository countryRepository) {
        this.townRepository = townRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public void seedTowns() throws IOException {
        if (this.townRepository.count() == 0) {
            Files.readAllLines(Path.of(FILE_PATH))
                    .stream()
                    .filter(row -> !row.isEmpty())
                    .forEach(row -> {
                        String[] parts = row.split(", ");
                        String townName = parts[0];
                        String countryName = parts[1];
                        Country country = this.countryRepository.findByName(countryName);
                        Town town = new Town(townName, country);
                        this.townRepository.saveAndFlush(town);
                    });
        }
    }

    @Override
    public Town getTownByName(String name) {
        return this.townRepository.findByName(name);
    }

    @Override
    public void createTown(Town town) {
        this.townRepository.saveAndFlush(town);
    }
}
