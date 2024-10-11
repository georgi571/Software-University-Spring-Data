package _8SpringData._1SpringData._8JSONProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.data.repositories;

import _8SpringData._1SpringData._8JSONProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.data.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "FROM Category c ORDER BY SIZE(c.products) DESC")
    Set<Category> findAllByCategoriesByProducts();
}
