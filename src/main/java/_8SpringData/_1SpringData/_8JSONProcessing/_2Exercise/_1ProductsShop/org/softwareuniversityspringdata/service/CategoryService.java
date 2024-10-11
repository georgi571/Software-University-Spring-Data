package _8SpringData._1SpringData._8JSONProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service;

import _8SpringData._1SpringData._8JSONProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.CategoryByProductsDTO;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    void seedCategories() throws IOException;
    List<CategoryByProductsDTO> getAllCategoryByProducts();
    void printAllCategoryByProduct();
}
