package _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service;

import jakarta.xml.bind.JAXBException;

public interface UserService {
    void seedUsers() throws JAXBException;
    void exportUsersAndSoldItems() throws JAXBException;
    void exportUserAndProducts() throws JAXBException;
}
