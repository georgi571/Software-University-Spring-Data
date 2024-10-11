package _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service;


import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.SalesWithAppliedDiscount.SalesDiscountDTO;

import java.io.IOException;
import java.util.List;

public interface SaleService {
    void seedSales();
    void exportSales() throws IOException;
    List<SalesDiscountDTO> getAllSalesInfo();
}
