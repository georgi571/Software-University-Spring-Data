package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.domain.dtos.xml.ProductSeedDTO;
import hiberspring.domain.dtos.xml.ProductSeedRootDTO;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Product;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.ProductRepository;
import hiberspring.service.ProductService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final String PATH_INPUT = "src/main/resources/files/products.xml";

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;


    public ProductServiceImpl(ProductRepository productRepository, BranchRepository branchRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean productsAreImported() {
        return this.productRepository.count() > 0;
    }

    @Override
    public String readProductsXmlFile() throws IOException {
        return this.fileUtil.readFile(PATH_INPUT);
    }

    @Override
    public String importProducts() throws JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        ProductSeedRootDTO productSeedRootDTO = this.xmlParser.parseXml(ProductSeedRootDTO.class, PATH_INPUT);
        for (ProductSeedDTO productSeedDTO : productSeedRootDTO.getProductSeedDTOList()) {
            Optional<Product> productOptional = this.productRepository.findByName(productSeedDTO.getName());
            Optional<Branch> branchOptional = this.branchRepository.findByName(productSeedDTO.getBranch());
            if (!this.validationUtil.isValid(productSeedDTO) || productOptional.isPresent() || branchOptional.isEmpty()) {
                stringBuilder.append("Error: Invalid data.").append(System.lineSeparator());
                continue;
            }
            Product product = this.modelMapper.map(productSeedDTO, Product.class);
            product.setBranch(branchOptional.get());
            this.productRepository.saveAndFlush(product);
            stringBuilder.append(String.format("Successfully imported Product %s.", product.getName())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
