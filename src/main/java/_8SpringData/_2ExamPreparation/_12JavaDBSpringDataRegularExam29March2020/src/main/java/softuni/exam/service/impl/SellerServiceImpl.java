package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.SellerSeedDTO;
import softuni.exam.models.dto.xml.SellerSeedRootDTO;
import softuni.exam.models.enity.Rating;
import softuni.exam.models.enity.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {

    private final String PATH_INPUT = "src/main/resources/files/xml/sellers.xml";

    private final SellerRepository sellerRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public SellerServiceImpl(SellerRepository sellerRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.sellerRepository = sellerRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        SellerSeedRootDTO sellerSeedRootDTO = this.xmlParser.parse(SellerSeedRootDTO.class, PATH_INPUT);
        for (SellerSeedDTO sellerSeedDTO : sellerSeedRootDTO.getSellerSeedDTOS()) {
            Optional<Seller> deviceOptional = this.sellerRepository.findByEmail(sellerSeedDTO.getEmail());
            Rating rating;
            try {
                rating = Rating.valueOf(sellerSeedDTO.getRating());
            } catch (IllegalArgumentException e) {
                rating = null;
            }
            if (!this.validationUtil.isValid(sellerSeedDTO) || deviceOptional.isPresent() || rating == null) {
                stringBuilder.append("Invalid seller").append(System.lineSeparator());
                continue;
            }
            Seller seller = this.modelMapper.map(sellerSeedDTO, Seller.class);
            seller.setRating(rating);
            this.sellerRepository.saveAndFlush(seller);
            stringBuilder.append(String.format("Successfully import seller %s - %s", seller.getLastName(), seller.getEmail())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
