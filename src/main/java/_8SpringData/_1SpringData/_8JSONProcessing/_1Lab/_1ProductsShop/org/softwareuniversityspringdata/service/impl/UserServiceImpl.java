package _8SpringData._1SpringData._8JSONProcessing._1Lab._1ProductsShop.org.softwareuniversityspringdata.service.impl;

import _8SpringData._1SpringData._8JSONProcessing._1Lab._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.*;
import _8SpringData._1SpringData._8JSONProcessing._1Lab._1ProductsShop.org.softwareuniversityspringdata.service.dtos.imports.UserSeedDTO;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import _8SpringData._1SpringData._8JSONProcessing._1Lab._1ProductsShop.org.softwareuniversityspringdata.data.entities.User;
import _8SpringData._1SpringData._8JSONProcessing._1Lab._1ProductsShop.org.softwareuniversityspringdata.data.repositories.UserRepository;
import _8SpringData._1SpringData._8JSONProcessing._1Lab._1ProductsShop.org.softwareuniversityspringdata.service.UserService;
import _8SpringData._1SpringData._8JSONProcessing._1Lab._1ProductsShop.org.softwareuniversityspringdata.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final String FILE_PATH = "src/main/resources/json/users.json";
    private final UserRepository userRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedUsers() throws FileNotFoundException {
        if (this.userRepository.count() == 0) {
            UserSeedDTO[] userSeedDTOs = this.gson.fromJson(new FileReader(FILE_PATH), UserSeedDTO[].class);

            for (UserSeedDTO userSeedDTO : userSeedDTOs) {

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
    public List<UserSoldProductsDTO> getAllUsersAndSoldItems() {
        return this.userRepository.findAll()
                .stream()
                .filter(user -> user.getSold().stream().anyMatch(product -> product.getBuyer() != null))
                .map(user -> {
                    UserSoldProductsDTO userDTO = this.modelMapper.map(user, UserSoldProductsDTO.class);
                    List<ProductInfoDTO.ProductSoldDTO> soldProductsDTO = user.getSold()
                            .stream()
                            .filter(product -> product.getBuyer() != null)
                            .map(product -> this.modelMapper.map(product, ProductInfoDTO.ProductSoldDTO.class))
                            .collect(Collectors.toList());
                    userDTO.setSoldProducts(soldProductsDTO);
                    return userDTO;
                })
                .sorted(Comparator.comparing(UserSoldProductsDTO::getLastName).thenComparing(UserSoldProductsDTO::getFirstName, Comparator.nullsLast(String::compareTo)))
                .collect(Collectors.toList());
    }

    @Override
    public void printAllUsersAndSoldItems() {
        String json = this.gson.toJson(this.getAllUsersAndSoldItems());
        System.out.printf("%s%n", json);
    }

    @Override
    public UserAndProductsDTO getUserAndProductsDTO() {
        UserAndProductsDTO userAndProductsDTO = new UserAndProductsDTO();
        List<UserSoldDTO> userSoldDTOs = this.userRepository.findAll()
                .stream()
                .filter(user -> !user.getSold().isEmpty())
                .map(user -> {
                    UserSoldDTO userSoldDTO = this.modelMapper.map(user, UserSoldDTO.class);

                    ProductSoldByUserDTO productSoldByUserDTO = new ProductSoldByUserDTO();
                    List<ProductInfoDTO> productInfoDTOs = user.getSold()
                            .stream()
                            .map(product -> this.modelMapper.map(product, ProductInfoDTO.class))
                            .collect(Collectors.toList());
                    productSoldByUserDTO.setProducts(productInfoDTOs);
                    productSoldByUserDTO.setCount(productInfoDTOs.size());

                    userSoldDTO.setSoldProduct(productSoldByUserDTO);
                    return userSoldDTO;
                })
                .sorted(Comparator
                        .comparingInt((UserSoldDTO a) -> a.getSoldProduct().getCount())
                        .reversed()
                        .thenComparing(UserSoldDTO::getLastName)
                )
                .collect(Collectors.toList());
        userAndProductsDTO.setUsers(userSoldDTOs);
        userAndProductsDTO.setUsersCount(userSoldDTOs.size());
        return userAndProductsDTO;
    }

    @Override
    public void printUserAndProductsDTO() {
        String json = this.gson.toJson(this.getUserAndProductsDTO());
        System.out.printf("%s%n", json);
    }
}
