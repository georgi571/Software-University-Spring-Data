package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.SellerSeedDTO;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {

    private static final String PATH_INPUT = "src/main/resources/files/json/sellers.json";

    private final SellerRepository sellerRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public SellerServiceImpl(SellerRepository sellerRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.sellerRepository = sellerRepository;
        this.gson = gson;
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
    public String importSellers() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        SellerSeedDTO[] sellerSeedDTOS = this.gson.fromJson(new FileReader(PATH_INPUT), SellerSeedDTO[].class);
        for (SellerSeedDTO sellerSeedDTO : sellerSeedDTOS) {
            Optional<Seller> sellerOptional = this.sellerRepository.findByLastName(sellerSeedDTO.getLastName());
            if (!this.validationUtil.isValid(sellerSeedDTO) || sellerOptional.isPresent()) {
                stringBuilder.append("Invalid seller").append(System.lineSeparator());
                continue;
            }
            Seller seller = this.modelMapper.map(sellerSeedDTO, Seller.class);
            this.sellerRepository.saveAndFlush(seller);
            stringBuilder.append(String.format("Successfully imported seller %s %s", seller.getFirstName(), seller.getLastName())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
