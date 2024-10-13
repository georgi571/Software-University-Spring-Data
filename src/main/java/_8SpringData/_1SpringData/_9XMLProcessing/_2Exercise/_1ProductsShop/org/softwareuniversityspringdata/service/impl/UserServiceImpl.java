package _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.impl;

import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.UserService;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.userAndProducts.SoldProductDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.userAndProducts.SoldProductsRootDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.userAndProducts.UserAndProductsDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.userAndProducts.UserAndProductsRootDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.userSoldProducts.ProductInfoDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.userSoldProducts.ProductInfoRootDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.userSoldProducts.UserSoldProductsDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.userSoldProducts.UserSoldProductsRootDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.imports.UserSeedDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.imports.UserSeedRootDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.util.ValidationUtil;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.util.XmlParser;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.data.entities.User;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.data.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final String FILE_INPUT_PATH = "src/main/resources/xml/imports/users.xml";
    private static final String FILE_OUTPUT_PATH_USERS_SOLD_PRODUCTS = "src/main/resources/xml/exports/users-sold-products.xml";
    private static final String FILE_OUTPUT_PATH_USERS_AND_PRODUCTS = "src/main/resources/xml/exports/users-and-products.xml";
    private final UserRepository userRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedUsers() throws JAXBException {
        if (this.userRepository.count() == 0) {
            UserSeedRootDTO userSeedRootDTO = xmlParser.parse(UserSeedRootDTO.class, FILE_INPUT_PATH);

            for (UserSeedDTO userSeedDTO : userSeedRootDTO.getUserSeedDTOList()) {
                if (!this.validationUtil.isValid(userSeedDTO)) {
                    this.validationUtil.getViolations(userSeedDTO)
                            .forEach(validationUtil -> System.out.printf("%s%n", validationUtil.getMessage()));
                    continue;
                }
                User user = this.modelMapper.map(userSeedDTO, User.class);
                this.userRepository.saveAndFlush(user);
            }
        }
    }

    @Override
    public void exportUsersAndSoldItems() throws JAXBException {
        List<UserSoldProductsDTO> userSoldProductsDTOList = this.userRepository.findAll()
                .stream()
                .filter(user -> user.getSold().stream().anyMatch(product -> product.getBuyer() != null))
                .map(user -> {
                    UserSoldProductsDTO userDTO = this.modelMapper.map(user, UserSoldProductsDTO.class);
                    ProductInfoRootDTO productInfoRootDTO = new ProductInfoRootDTO();
                    List<ProductInfoDTO> productInfoDTOS = user.getSold()
                            .stream()
                            .filter(product -> product.getBuyer() != null)
                            .map(product -> this.modelMapper.map(product, ProductInfoDTO.class))
                            .collect(Collectors.toList());
                    productInfoRootDTO.setProductInfoDTOList(productInfoDTOS);
                    userDTO.setProductInfoRootDTO(productInfoRootDTO);
                    return userDTO;
                })
                .sorted(Comparator.comparing(UserSoldProductsDTO::getLastName).thenComparing(UserSoldProductsDTO::getFirstName, Comparator.nullsLast(String::compareTo)))
                .collect(Collectors.toList());

        UserSoldProductsRootDTO userSoldProductsRootDTO = new UserSoldProductsRootDTO();
        userSoldProductsRootDTO.setUserSoldProductsDTOS(userSoldProductsDTOList);
        this.xmlParser.exportToFile(UserSoldProductsRootDTO.class, userSoldProductsRootDTO, FILE_OUTPUT_PATH_USERS_SOLD_PRODUCTS);
    }

    @Override
    public void exportUserAndProducts() throws JAXBException {
        List<UserAndProductsDTO> userAndProductsDTOList = this.userRepository.findAll()
                .stream()
                .filter(user -> !user.getSold().isEmpty())
                .map(user -> {
                    UserAndProductsDTO userAndProductsDTO = this.modelMapper.map(user, UserAndProductsDTO.class);

                    SoldProductsRootDTO soldProductsRootDTO = new SoldProductsRootDTO();
                    List<SoldProductDTO> soldProductDTOList = user.getSold()
                            .stream()
                            .map(product -> this.modelMapper.map(product, SoldProductDTO.class))
                            .collect(Collectors.toList());
                    soldProductsRootDTO.setSoldProductDTOList(soldProductDTOList);
                    soldProductsRootDTO.setCount(soldProductDTOList.size());

                    userAndProductsDTO.setSoldProductsRootDTO(soldProductsRootDTO);
                    return userAndProductsDTO;
                })
                .collect(Collectors.toList());

        UserAndProductsRootDTO userAndProductsRootDTO = new UserAndProductsRootDTO();
        userAndProductsRootDTO.setUserAndProductsDTOList(userAndProductsDTOList);
        userAndProductsRootDTO.setCount(userAndProductsDTOList.size());

        this.xmlParser.exportToFile(UserAndProductsRootDTO.class, userAndProductsRootDTO, FILE_OUTPUT_PATH_USERS_AND_PRODUCTS);
    }
}
