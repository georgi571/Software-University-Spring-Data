package _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service;

import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CarAndParts.CarAndPartsDTO;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CarMake.CarMakeDTO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface CarService {
    void seedCars() throws FileNotFoundException;

    void exportModelCars(String model) throws IOException;

    void exportCarAndParts() throws IOException;

    List<CarMakeDTO> getAllCarsByMake(String make);

    List<CarAndPartsDTO> getAllCarsAndParts();
}
