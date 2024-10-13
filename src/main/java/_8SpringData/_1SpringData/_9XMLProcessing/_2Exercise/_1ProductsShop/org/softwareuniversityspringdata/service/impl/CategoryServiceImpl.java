package _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.impl;

import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.data.entities.Category;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.data.entities.Product;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.CategoryService;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.catergoryByProducts.CategoryByProductRootDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.catergoryByProducts.CategoryByProductsDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.imports.CategorySeedDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.imports.CategorySeedRootDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.util.ValidationUtil;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.util.XmlParser;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.data.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String FILE_INPUT_PATH = "src/main/resources/xml/imports/categories.xml";
    private static final String FILE_OUTPUT_PATH = "src/main/resources/xml/exports/categories-by-products.xml";
    private final CategoryRepository categoryRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories() throws JAXBException {
        if (this.categoryRepository.count() == 0) {
            CategorySeedRootDTO categorySeedRootDTO = this.xmlParser.parse(CategorySeedRootDTO.class, FILE_INPUT_PATH);

            for (CategorySeedDTO categorySeedDTO : categorySeedRootDTO.getCategorySeedDTOList()) {
                if (!this.validationUtil.isValid(categorySeedDTO)) {
                    this.validationUtil.getViolations(categorySeedDTO)
                            .forEach(validationUtil -> System.out.printf("%s%n", validationUtil.getMessage()));
                    continue;
                }

                Category category = this.modelMapper.map(categorySeedDTO, Category.class);
                this.categoryRepository.saveAndFlush(category);
            }
        }
    }

    @Override
    public void exportAllCategoryByProduct() throws JAXBException {
        List<CategoryByProductsDTO> categoryByProductsDTOList = this.categoryRepository.findAllByCategoriesByProducts()
                .stream()
                .map(category -> {
                    CategoryByProductsDTO categoryDTO = this.modelMapper.map(category, CategoryByProductsDTO.class);
                    categoryDTO.setProductCount(category.getProducts().size());
                    BigDecimal sum = category.getProducts().stream().map(Product::getPrice).reduce(BigDecimal::add).get();
                    categoryDTO.setTotalRevenue(sum);
                    categoryDTO.setAveragePrice(sum.divide(BigDecimal.valueOf(category.getProducts().size()), MathContext.DECIMAL64));
                    return categoryDTO;
                })
                .collect(Collectors.toList());

        CategoryByProductRootDTO categoryByProductRootDTO = new CategoryByProductRootDTO();
        categoryByProductRootDTO.setCategoryByProductsDTOList(categoryByProductsDTOList);
        this.xmlParser.exportToFile(CategoryByProductRootDTO.class, categoryByProductRootDTO, FILE_OUTPUT_PATH);
    }
}
