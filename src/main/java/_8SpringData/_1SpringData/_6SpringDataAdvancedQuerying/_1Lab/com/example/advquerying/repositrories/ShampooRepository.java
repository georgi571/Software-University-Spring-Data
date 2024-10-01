package _8SpringData._1SpringData._6SpringDataAdvancedQuerying._1Lab.com.example.advquerying.repositrories;

import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._1Lab.com.example.advquerying.entities.Shampoo;
import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._1Lab.com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    Set<Shampoo> findAllBySizeOrderById(Size size); // 1st exercise
    Set<Shampoo> findAllBySizeOrLabelIdOrderByPrice(Size size, long id); // 2nd exercise
    Set<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price); // 3rd exercise
    Set<Shampoo> findAllByPriceLessThan(BigDecimal price); // 6th exercise
    Set<Shampoo> findAllByIngredientsNameIn(List<String> names); // 7th exercise
    @Query("FROM Shampoo WHERE SIZE(ingredients) < :number ")
    Set<Shampoo> findAllWithIngredientsCountLesserThan(int number); // 8th exercise
}
