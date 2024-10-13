package _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata;

import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.CategoryService;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.ProductService;
import _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;

    public CommandLineRunnerImpl(UserService userService, CategoryService categoryService, ProductService productService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.userService.seedUsers();
        this.categoryService.seedCategories();
        this.productService.seedProducts();

        this.productService.exportProductsInRange(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
        this.userService.exportUsersAndSoldItems();
        this.categoryService.exportAllCategoryByProduct();
        this.userService.exportUserAndProducts();
    }
}
