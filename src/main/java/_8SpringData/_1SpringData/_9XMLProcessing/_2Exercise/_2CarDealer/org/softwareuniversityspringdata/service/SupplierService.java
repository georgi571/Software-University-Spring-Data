package _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service;

import jakarta.xml.bind.JAXBException;

public interface SupplierService {
    void seedSupplier() throws JAXBException;
    void exportLocalSuppliers() throws JAXBException;
}
