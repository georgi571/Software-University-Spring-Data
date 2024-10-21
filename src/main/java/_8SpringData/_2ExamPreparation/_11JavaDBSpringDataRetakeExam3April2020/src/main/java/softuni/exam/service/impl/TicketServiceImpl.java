package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.TicketSeedDTO;
import softuni.exam.models.dto.xml.TicketSeedRootDTO;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Plane;
import softuni.exam.models.entities.Ticket;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.repository.TicketRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TicketService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final String PATH_INPUT = "src/main/resources/files/xml/tickets.xml";

    private final TicketRepository ticketRepository;
    private final TownRepository townRepository;
    private final PassengerRepository passengerRepository;
    private final PlaneRepository planeRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public TicketServiceImpl(TicketRepository ticketRepository, TownRepository townRepository, PassengerRepository passengerRepository, PlaneRepository planeRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.ticketRepository = ticketRepository;
        this.townRepository = townRepository;
        this.passengerRepository = passengerRepository;
        this.planeRepository = planeRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.ticketRepository.count() > 0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importTickets() throws JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        TicketSeedRootDTO ticketSeedRootDTO = this.xmlParser.parse(TicketSeedRootDTO.class, PATH_INPUT);
        for (TicketSeedDTO ticketSeedDTO : ticketSeedRootDTO.getTicketSeedDTOS()) {
            Optional<Ticket> ticketOptional = this.ticketRepository.findBySerialNumber(ticketSeedDTO.getSerialNumber());
            Optional<Town> fromTownOptional = this.townRepository.findByName(ticketSeedDTO.getFromTown().getName());
            Optional<Town> toTownOptional = this.townRepository.findByName(ticketSeedDTO.getToTown().getName());
            Optional<Passenger> passengerOptional = this.passengerRepository.findByEmail(ticketSeedDTO.getPassenger().getEmail());
            Optional<Plane> planeOptional = this.planeRepository.findByRegisterNumber(ticketSeedDTO.getPlane().getRegisterNumber());
            if (!this.validationUtil.isValid(ticketSeedDTO) || ticketOptional.isPresent() || fromTownOptional.isEmpty() || toTownOptional.isEmpty() || passengerOptional.isEmpty() || planeOptional.isEmpty()) {
                stringBuilder.append("Invalid Ticket").append(System.lineSeparator());
                continue;
            }
            Ticket ticket = this.modelMapper.map(ticketSeedDTO, Ticket.class);
            Town fromTown = fromTownOptional.get();
            Town toTown = toTownOptional.get();
            Passenger passenger = passengerOptional.get();
            Plane plane = planeOptional.get();
            ticket.setFromTown(fromTown);
            ticket.setToTown(toTown);
            ticket.setPassenger(passenger);
            ticket.setPlane(plane);
            this.ticketRepository.saveAndFlush(ticket);
            stringBuilder.append(String.format("Successfully imported Ticket %s - %s", ticket.getFromTown(), ticket.getToTown())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
