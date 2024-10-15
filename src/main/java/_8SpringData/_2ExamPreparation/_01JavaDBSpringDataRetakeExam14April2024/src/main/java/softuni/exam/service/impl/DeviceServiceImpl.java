package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.SaleSeedDto;
import softuni.exam.models.dto.xmls.DeviceSeedDTO;
import softuni.exam.models.dto.xmls.DeviceSeedRootDTO;
import softuni.exam.models.entity.Device;
import softuni.exam.models.entity.DeviceType;
import softuni.exam.models.entity.Sale;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.DeviceRepository;
import softuni.exam.repository.SaleRepository;
import softuni.exam.service.DeviceService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {

    private static final String PATH_INPUT = "src/main/resources/files/xml/devices.xml";

    private final DeviceRepository deviceRepository;
    private final SaleRepository saleRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public DeviceServiceImpl(DeviceRepository deviceRepository, SaleRepository saleRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.deviceRepository = deviceRepository;
        this.saleRepository = saleRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.deviceRepository.count() > 0;
    }

    @Override
    public String readDevicesFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importDevices() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        DeviceSeedRootDTO deviceSeedRootDTO = this.xmlParser.fromFile(PATH_INPUT, DeviceSeedRootDTO.class);
        for (DeviceSeedDTO deviceSeedDTO : deviceSeedRootDTO.getSeedDTOList()) {
            Optional<Device> deviceOptional = this.deviceRepository.findByBrandAndModel(deviceSeedDTO.getBrand(), deviceSeedDTO.getModel());
            Optional<Sale> saleOptional = this.saleRepository.findById(deviceSeedDTO.getSale_id());
            if (!this.validationUtil.isValid(deviceSeedDTO) || deviceOptional.isPresent() || saleOptional.isEmpty()) {
                stringBuilder.append("Invalid device").append(System.lineSeparator());
                continue;
            }
            Device device = this.modelMapper.map(deviceSeedDTO, Device.class);
            DeviceType deviceType = DeviceType.valueOf(deviceSeedDTO.getDeviceType());
            device.setDeviceType(deviceType);
            device.setSale(saleOptional.get());
            this.deviceRepository.saveAndFlush(device);
            stringBuilder.append(String.format("Successfully imported device of type %s with brand %s", device.getDeviceType(), device.getBrand())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }

    @Override
    public String exportDevices() {
        return this.deviceRepository.findAllByDeviceTypeAndPriceLessThanAndStorageIsGreaterThanEqualOrderByBrand(DeviceType.SMART_PHONE, 1000, 128)
                .stream()
                .map(device -> String.format("Device brand: %s\n" +
                        "   *Model: %s\n" +
                        "   **Storage: %d\n" +
                        "   ***Price: %.2f\n",
                        device.getBrand(),
                        device.getModel(),
                        device.getStorage(),
                        device.getPrice()))
                .collect(Collectors.joining());
    }
}
