package _8SpringData._1SpringData._8JSONProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.data.entities.Category;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.data.entities.Product;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.data.repositories.CategoryRepository;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.CategoryService;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.CategoryByProductsDTO;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.imports.CategorySeedDTO;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String FILE_PATH = "src/main/resources/json/categories.json";
    private final CategoryRepository categoryRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() == 0) {
            CategorySeedDTO[] categorySeedDTOs = this.gson.fromJson(new FileReader(FILE_PATH), CategorySeedDTO[].class);

            for (CategorySeedDTO categorySeedDTO : categorySeedDTOs) {

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
    public List<CategoryByProductsDTO> getAllCategoryByProducts() {
        return this.categoryRepository.findAllByCategoriesByProducts()
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
    }

    @Override
    public void printAllCategoryByProduct() {
        String json = this.gson.toJson(this.getAllCategoryByProducts());
        System.out.printf("%s%n", json);
    }
}
