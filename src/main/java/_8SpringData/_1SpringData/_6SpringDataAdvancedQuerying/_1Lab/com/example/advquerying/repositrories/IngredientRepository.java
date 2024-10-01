package _8SpringData._1SpringData._6SpringDataAdvancedQuerying._1Lab.com.example.advquerying.repositrories;

import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._1Lab.com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Set<Ingredient> findAllByNameStartsWith(String letters); // 4th exercise

    Set<Ingredient> findAllByNameInOrderByPrice(List<String> names); // 5th exercise

    @Transactional
    @Modifying
    @Query("DELETE Ingredient WHERE name = :name")
    int deleteIngredientByName(String name);  // 9th exercise

    @Transactional
    @Modifying
    @Query("UPDATE Ingredient SET price = price * :percent")
    int updateAllByPrice(double percent);  // 10th exercise

    @Transactional
    @Modifying
    @Query("UPDATE Ingredient SET price = price * :percent WHERE name IN :names")
    int updateAllByPriceForGivenName(double percent, List<String> names);  // 11th exercise
}
