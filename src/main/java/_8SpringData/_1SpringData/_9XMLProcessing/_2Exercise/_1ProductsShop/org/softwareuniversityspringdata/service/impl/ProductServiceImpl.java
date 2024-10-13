package _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.impl;

import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.data.entities.Category;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.data.entities.Product;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.ProductService;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.productsInRange.ProductInRangeDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports.productsInRange.ProductInRangeRootDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.imports.ProductSeedDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.imports.ProductSeedRootDTO;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.util.ValidationUtil;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.util.XmlParser;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.data.entities.User;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.data.repositories.CategoryRepository;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.data.repositories.ProductRepository;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.data.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private static final String FILE_INPUT_PATH = "src/main/resources/xml/imports/products.xml";
    private static final String FILE_OUTPUT_PATH = "src/main/resources/xml/exports/products-in-range.xml";
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedProducts() throws JAXBException {
        if (this.productRepository.count() == 0) {
            ProductSeedRootDTO productSeedRootDTO = this.xmlParser.parse(ProductSeedRootDTO.class, FILE_INPUT_PATH);

            for (ProductSeedDTO productSeedDTO : productSeedRootDTO.getProductSeedDTOList()) {
                if (!this.validationUtil.isValid(productSeedDTO)) {
                    this.validationUtil.getViolations(productSeedDTO)
                            .forEach(validationUtil -> System.out.printf("%s%n", validationUtil.getMessage()));
                    continue;
                }
                Product product = this.modelMapper.map(productSeedDTO, Product.class);
                product.setBuyer(getRandomUser(true));
                product.setSeller(getRandomUser(false));
                product.setCategories(getRandomCategories());
                this.productRepository.saveAndFlush(product);
            }
        }
    }

    @Override
    public void exportProductsInRange(BigDecimal from, BigDecimal to) throws JAXBException {
        List<ProductInRangeDTO> productInRangeDTOList = this.productRepository.findAllByPriceBetweenAndBuyerIsNull(from, to)
                .stream()
                .map(product -> {
                    ProductInRangeDTO productInRangeDTO = this.modelMapper.map(product, ProductInRangeDTO.class);
                    productInRangeDTO.setSeller(String.format("%s %s", product.getSeller().getFirstName(), product.getSeller().getLastName()));
                    return productInRangeDTO;
                })
                .sorted(Comparator.comparing(ProductInRangeDTO::getPrice))
                .collect(Collectors.toList());

        ProductInRangeRootDTO productInRangeRootDTO = new ProductInRangeRootDTO();
        productInRangeRootDTO.setProductInRangeDTOList(productInRangeDTOList);

        this.xmlParser.exportToFile(ProductInRangeRootDTO.class, productInRangeRootDTO, FILE_OUTPUT_PATH);
    }

    private Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();

        int randomCount = ThreadLocalRandom.current().nextInt(1, 4);

        for (int i = 0; i < randomCount; i++) {
            long randomId = ThreadLocalRandom.current().nextLong(1, this.categoryRepository.count() + 1);
            Category category = this.categoryRepository.findById(randomId).get();
            categories.add(category);
        }
        return categories;
    }

    private User getRandomUser(boolean isBuyer) {
        long randomId = ThreadLocalRandom.current().nextLong(1, this.userRepository.count() + 1);

        User user;

        if (isBuyer && randomId % 4 == 0) {
            user = null;
        } else {
            user = this.userRepository.findById(randomId).get();
        }

        return user;
    }
}
