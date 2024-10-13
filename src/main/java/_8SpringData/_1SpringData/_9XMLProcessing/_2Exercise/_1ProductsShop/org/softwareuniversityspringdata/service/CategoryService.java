package _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service;

import jakarta.xml.bind.JAXBException;

public interface CategoryService {
    void seedCategories() throws JAXBException;
    void exportAllCategoryByProduct() throws JAXBException;
}
