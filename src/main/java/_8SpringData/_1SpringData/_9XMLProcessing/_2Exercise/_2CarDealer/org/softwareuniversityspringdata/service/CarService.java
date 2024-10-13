package _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service;

import jakarta.xml.bind.JAXBException;

public interface CarService {
    void seedCars() throws JAXBException;
    void exportModelCars(String model) throws JAXBException;
    void exportCarAndParts() throws JAXBException;
}
