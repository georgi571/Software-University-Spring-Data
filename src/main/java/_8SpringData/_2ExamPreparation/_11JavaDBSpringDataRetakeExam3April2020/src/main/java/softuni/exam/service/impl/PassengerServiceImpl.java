package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.PassengerSeedDTO;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService {

    private final String PATH_INPUT = "src/main/resources/files/json/passengers.json";

    private final PassengerRepository passengerRepository;
    private final TownRepository townRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public PassengerServiceImpl(PassengerRepository passengerRepository, TownRepository townRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.passengerRepository = passengerRepository;
        this.townRepository = townRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.passengerRepository.count() > 0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importPassengers() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        PassengerSeedDTO[] passengerSeedDTOS = this.gson.fromJson(new FileReader(PATH_INPUT), PassengerSeedDTO[].class);
        for (PassengerSeedDTO passengerSeedDTO : passengerSeedDTOS) {
            Optional<Passenger> passengerOptional = this.passengerRepository.findByEmail(passengerSeedDTO.getEmail());
            if (!this.validationUtil.isValid(passengerSeedDTO) || passengerOptional.isPresent()) {
                stringBuilder.append("Invalid Passenger").append(System.lineSeparator());
                continue;
            }
            Passenger passenger = this.modelMapper.map(passengerSeedDTO, Passenger.class);
            Town town = this.townRepository.findByName(passengerSeedDTO.getTown()).get();
            passenger.setTown(town);
            this.passengerRepository.saveAndFlush(passenger);
            stringBuilder.append(String.format("Successfully imported Passenger %s - %s", passenger.getLastName(), passenger.getEmail())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {
        return this.passengerRepository.findAllOrderdByTicketCountDescEmailAsc()
                .stream()
                .map(passenger -> String.format("Passenger %s  %s\n" +
                                "         Email - %s\n" +
                                "         Phone - %s\n" +
                                "         Number of tickets - %d\n\n",
                        passenger.getFirstName(), passenger.getLastName(),
                        passenger.getEmail(),
                        passenger.getPhoneNumber(),
                        passenger.getTicketSet().size()))
                .collect(Collectors.joining());
    }
}
