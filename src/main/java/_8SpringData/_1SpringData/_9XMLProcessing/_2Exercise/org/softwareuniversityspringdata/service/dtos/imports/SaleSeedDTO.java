package _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.imports;

import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.data.entities.Car;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.data.entities.Customer;

import java.io.Serializable;

public class SaleSeedDTO implements Serializable {
    private Car car;
    private Customer customer;
    private double discount;
}
