package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.xml.ShopSeedDTO;
import exam.model.dto.xml.ShopSeedRootDTO;
import exam.model.entity.Shop;
import exam.model.entity.Town;
import exam.repository.ShopRepository;
import exam.repository.TownRepository;
import exam.service.ShopService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {

    private final String PATH_INPUT = "src/main/resources/files/xml/shops.xml";

    private final ShopRepository shopRepository;
    private final TownRepository townRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public ShopServiceImpl(ShopRepository shopRepository, TownRepository townRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.shopRepository = shopRepository;
        this.townRepository = townRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();

        ShopSeedRootDTO shopSeedRootDTO = this.xmlParser.parse(ShopSeedRootDTO.class, PATH_INPUT);
        for (ShopSeedDTO shopSeedDTO : shopSeedRootDTO.getShopSeedDTOList()) {
            Optional<Shop> shopOptional = this.shopRepository.findByName(shopSeedDTO.getName());
            if (!this.validationUtil.isValid(shopSeedDTO) || shopOptional.isPresent()) {
                stringBuilder.append("Invalid shop").append(System.lineSeparator());
                continue;
            }
            Shop shop = this.modelMapper.map(shopSeedDTO, Shop.class);
            Town town = this.townRepository.findByName(shopSeedDTO.getTownShopDTO().getName()).get();
            shop.setTown(town);
            this.shopRepository.saveAndFlush(shop);
            stringBuilder.append(String.format("Successfully imported Shop %s - %.0f", shop.getName(), shop.getIncome())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
