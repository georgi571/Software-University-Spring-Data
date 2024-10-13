package _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service;

import jakarta.xml.bind.JAXBException;

import java.math.BigDecimal;

public interface ProductService {
    void seedProducts() throws JAXBException;
    void exportProductsInRange(BigDecimal from, BigDecimal to) throws JAXBException;
}
