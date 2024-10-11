package _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service;

import jakarta.xml.bind.JAXBException;

public interface CustomerService {
    void seedCustomers() throws JAXBException;

    void exportOrderedCustomers() throws JAXBException;
    void exportCustomersWithBoughtCars() throws JAXBException;
}
