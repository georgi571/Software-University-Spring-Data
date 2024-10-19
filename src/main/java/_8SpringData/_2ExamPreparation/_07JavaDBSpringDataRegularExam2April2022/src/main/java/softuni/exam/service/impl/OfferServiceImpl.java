package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.OfferSeedDTO;
import softuni.exam.models.dto.xml.OffersSeedRootDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.ApartmentType;
import softuni.exam.models.entity.Offer;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final String PATH_INPUT = "src/main/resources/files/xml/offers.xml";

    private final OfferRepository offerRepository;
    private final AgentRepository agentRepository;
    private final ApartmentRepository apartmentRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public OfferServiceImpl(OfferRepository offerRepository, AgentRepository agentRepository, ApartmentRepository apartmentRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.offerRepository = offerRepository;
        this.agentRepository = agentRepository;
        this.apartmentRepository = apartmentRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        OffersSeedRootDTO offersSeedRootDTO = this.xmlParser.parse(OffersSeedRootDTO.class, PATH_INPUT);
        for (OfferSeedDTO offerSeedDTO : offersSeedRootDTO.getOfferSeedDTOS()) {
            Optional<Agent> agentOptional = this.agentRepository.findByFirstName(offerSeedDTO.getAgent().getName());
            if (!this.validationUtil.isValid(offerSeedDTO) || agentOptional.isEmpty()) {
                stringBuilder.append("Invalid offer").append(System.lineSeparator());
                continue;
            }
            Offer offer = this.modelMapper.map(offerSeedDTO, Offer.class);
            Agent agent = agentOptional.get();
            Apartment apartment = this.apartmentRepository.getById(offerSeedDTO.getApartment().getId());
            offer.setAgent(agent);
            offer.setApartment(apartment);
            this.offerRepository.saveAndFlush(offer);
            stringBuilder.append(String.format("Successfully imported offer %.2f", offer.getPrice())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }

    @Override
    public String exportOffers() {
        return this.offerRepository.findAllByApartmentTypeOrderByAreaDescPriceAsc(ApartmentType.three_rooms)
                .stream()
                .map(offer -> String.format("Agent %s %s with offer №%d:\n" +
                                "   -Apartment area: %.2f\n" +
                                "   --Town: %s\n" +
                                "   ---Price: %.2f$\n",
                        offer.getAgent().getFirstName(), offer.getAgent().getLastName(), offer.getId(),
                        offer.getApartment().getArea(),
                        offer.getApartment().getTown().getTownName(),
                        offer.getPrice()))
                .collect(Collectors.joining());
    }
}
