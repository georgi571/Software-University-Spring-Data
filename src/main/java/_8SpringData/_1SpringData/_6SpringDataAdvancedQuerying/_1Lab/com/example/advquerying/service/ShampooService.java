package _8SpringData._1SpringData._6SpringDataAdvancedQuerying._1Lab.com.example.advquerying.service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ShampooService {
    List<String> getAllShampooByGivenSize(String size);  // 1st exercise
    List<String> getAllShampooByGivenSizeOrLabel(String size, long id);  // 2nd exercise
    List<String> getAllShampooWithPriceHigherThan(BigDecimal price);  // 3rd exercise
    Long getCountOfAllShampooWithPriceLowerThan(BigDecimal price); // 6th exercise
    Set<String> getAllShampoosContainingIngredient(List<String> ingredients); // 7th exercise
    Set<String> getAllShampooWithCountOfIngredientsBelowNumber(int number);  // 8th exercise

}
