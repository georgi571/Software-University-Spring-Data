package _8SpringData._1SpringData._8JSONProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service;

import _8SpringData._1SpringData._8JSONProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.UserAndProductsDTO;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.UserSoldProductsDTO;

import java.io.FileNotFoundException;
import java.util.List;

public interface UserService {
    void seedUsers() throws FileNotFoundException;
    List<UserSoldProductsDTO> getAllUsersAndSoldItems();
    void printAllUsersAndSoldItems();
    UserAndProductsDTO getUserAndProductsDTO();
    void printUserAndProductsDTO();
}
