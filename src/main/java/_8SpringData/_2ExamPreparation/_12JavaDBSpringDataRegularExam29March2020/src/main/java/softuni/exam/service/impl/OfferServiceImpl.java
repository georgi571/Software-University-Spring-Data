package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.OfferSeedDTO;
import softuni.exam.models.dto.xml.OfferSeedRootDTO;
import softuni.exam.models.enity.Car;
import softuni.exam.models.enity.Offer;
import softuni.exam.models.enity.Picture;
import softuni.exam.models.enity.Seller;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OfferServiceImpl implements OfferService {

    private final String PATH_INPUT = "src/main/resources/files/xml/offers.xml";

    private final OfferRepository offerRepository;
    private final CarRepository carRepository;
    private final SellerRepository sellerRepository;
    private final PictureRepository pictureRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public OfferServiceImpl(OfferRepository offerRepository, CarRepository carRepository, SellerRepository sellerRepository, PictureRepository pictureRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.offerRepository = offerRepository;
        this.carRepository = carRepository;
        this.sellerRepository = sellerRepository;
        this.pictureRepository = pictureRepository;
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

        OfferSeedRootDTO offerSeedRootDTO = this.xmlParser.parse(OfferSeedRootDTO.class, PATH_INPUT);
        for (OfferSeedDTO offerSeedDTO : offerSeedRootDTO.getOfferSeedDTOS()) {
            Optional<Car> carOptional = this.carRepository.findById(offerSeedDTO.getCar().getId());
            Optional<Seller> sellerOptional = this.sellerRepository.findById(offerSeedDTO.getSeller().getId());
            if (!this.validationUtil.isValid(offerSeedDTO) || carOptional.isEmpty() || sellerOptional.isEmpty()) {
                stringBuilder.append("Invalid offer").append(System.lineSeparator());
                continue;
            }
            Offer offer = this.modelMapper.map(offerSeedDTO, Offer.class);
            offer.setCar(carOptional.get());
            offer.setSeller(sellerOptional.get());
            Set<Picture> pictureSet = carOptional.get().getPictures();
            offer.setPictureSet(new HashSet<>(pictureSet));
            this.offerRepository.saveAndFlush(offer);
            stringBuilder.append(String.format("Successfully import offer %s - %s", offer.getAddedOn(), offer.isHasGoldStatus())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
