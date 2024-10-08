package _8SpringData._1SpringData._8JSONProcessing._1Lab._1ProductsShop.org.softwareuniversityspringdata.service;

import _8SpringData._1SpringData._8JSONProcessing._1Lab._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.ProductInRangeDTO;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedProducts() throws FileNotFoundException;
    List<ProductInRangeDTO> getAllProductsInRange(BigDecimal from, BigDecimal to);
    void printAllProductsInRange(BigDecimal from, BigDecimal to);
}
