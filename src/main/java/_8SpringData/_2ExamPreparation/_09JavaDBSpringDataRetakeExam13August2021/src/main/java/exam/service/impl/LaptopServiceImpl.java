package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.json.LaptopSeedDTO;
import exam.model.entity.Laptop;
import exam.model.entity.Shop;
import exam.model.entity.WarrantyType;
import exam.repository.LaptopRepository;
import exam.repository.ShopRepository;
import exam.service.LaptopService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LaptopServiceImpl implements LaptopService {
    private final String PATH_INPUT = "src/main/resources/files/json/laptops.json";

    private final LaptopRepository laptopRepository;
    private final ShopRepository shopRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public LaptopServiceImpl(LaptopRepository laptopRepository, ShopRepository shopRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.laptopRepository = laptopRepository;
        this.shopRepository = shopRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));    }

    @Override
    public String importLaptops() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        LaptopSeedDTO[] laptopSeedDTOS = this.gson.fromJson(new FileReader(PATH_INPUT), LaptopSeedDTO[].class);
        for (LaptopSeedDTO laptopSeedDTO : laptopSeedDTOS) {
            Optional<Laptop> laptopOptional = this.laptopRepository.findByMacAddress(laptopSeedDTO.getMacAddress());
            WarrantyType warrantyType;
            try {
                warrantyType = WarrantyType.valueOf(laptopSeedDTO.getWarrantyType());
            } catch (IllegalArgumentException e) {
                warrantyType = null;
            }
            if (!this.validationUtil.isValid(laptopSeedDTO) || laptopOptional.isPresent() || warrantyType == null) {
                stringBuilder.append("Invalid laptop").append(System.lineSeparator());
                continue;
            }
            Laptop laptop = this.modelMapper.map(laptopSeedDTO, Laptop.class);
            Shop shop = this.shopRepository.findByName(laptopSeedDTO.getShop().getName()).get();
            laptop.setWarrantyType(warrantyType);
            laptop.setShop(shop);
            this.laptopRepository.saveAndFlush(laptop);
            stringBuilder.append(String.format("Successfully imported Laptop %s - %.2f - %d - %d", laptop.getMacAddress(), laptop.getCpuSpeed(), laptop.getRam(), laptop.getRam())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }

    @Override
    public String exportBestLaptops() {
        return this.laptopRepository.findAllOrderByCpuSpeedDescRamDescStorageDescMacAddressAsc()
                .stream()
                .map(laptop -> String.format("Laptop - %s\n" +
                                "*Cpu speed - %.2f\n" +
                                "**Ram - %d\n" +
                                "***Storage - %d\n" +
                                "****Price - %.2f\n" +
                                "#Shop name - %s\n" +
                                "##Town - %s\n\n",
                        laptop.getMacAddress(),
                        laptop.getCpuSpeed(),
                        laptop.getRam(),
                        laptop.getStorage(),
                        laptop.getPrice(),
                        laptop.getShop().getName(),
                        laptop.getShop().getTown().getName()))
                .collect(Collectors.joining());
    }
}
