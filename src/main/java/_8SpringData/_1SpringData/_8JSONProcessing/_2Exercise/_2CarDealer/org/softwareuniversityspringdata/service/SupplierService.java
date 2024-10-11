package _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service;

import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.SupplierLocal.SupplierLocalDTO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface SupplierService {
    void seedSupplier() throws FileNotFoundException;
    void exportLocalSuppliers() throws IOException;

    List<SupplierLocalDTO> getAllLocalSuppliers(boolean isLocalSupplier);
}
