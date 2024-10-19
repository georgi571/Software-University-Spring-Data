package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.ApartmentSeedDTO;
import softuni.exam.models.dto.xml.ApartmentSeedRootDTO;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.ApartmentType;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.ApartmentService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    private final String PATH_INPUT = "src/main/resources/files/xml/apartments.xml";

    private final ApartmentRepository apartmentRepository;
    private final TownRepository townRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, TownRepository townRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.apartmentRepository = apartmentRepository;
        this.townRepository = townRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importApartments() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        ApartmentSeedRootDTO apartmentSeedRootDTO = this.xmlParser.parse(ApartmentSeedRootDTO.class, PATH_INPUT);
        for (ApartmentSeedDTO apartmentSeedDTO : apartmentSeedRootDTO.getApartmentSeedDTOS()) {
            Optional<Town> townOptional = this.townRepository.findByTownName(apartmentSeedDTO.getTown());
            Optional<Apartment> deviceOptional = this.apartmentRepository.findByTownAndArea(townOptional.get(), apartmentSeedDTO.getArea());
            if (!this.validationUtil.isValid(apartmentSeedDTO) || deviceOptional.isPresent() || townOptional.isEmpty()) {
                stringBuilder.append("Invalid apartment").append(System.lineSeparator());
                continue;
            }
            Apartment apartment = this.modelMapper.map(apartmentSeedDTO, Apartment.class);
            Town town = townOptional.get();
            ApartmentType apartmentType = ApartmentType.valueOf(apartmentSeedDTO.getApartmentType());
            apartment.setTown(town);
            apartment.setApartmentType(apartmentType);
            this.apartmentRepository.saveAndFlush(apartment);
            stringBuilder.append(String.format("Successfully imported apartment %s - %.2f", apartment.getApartmentType(), apartment.getArea())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
