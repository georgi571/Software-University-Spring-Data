package _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service;

import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CustomerOrder.CustomerOrderedDTO;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CustomerTotalSales.CustomerTotalSaleDTO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface CustomerService {
    void seedCustomers() throws FileNotFoundException;

    void exportOrderedCustomers() throws IOException;

    List<CustomerOrderedDTO> getAllCustomers();

    void exportCustomersWithBoughtCars() throws IOException;

    List<CustomerTotalSaleDTO> getAllCustomersWithBoughtCars();
}
