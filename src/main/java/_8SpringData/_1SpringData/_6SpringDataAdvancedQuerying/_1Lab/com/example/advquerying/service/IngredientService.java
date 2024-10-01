package _8SpringData._1SpringData._6SpringDataAdvancedQuerying._1Lab.com.example.advquerying.service;

import java.util.List;

public interface IngredientService {
    List<String> getAllIngredientStartWithLetters(String letters);  // 4st exercise
    List<String> getAllContainingIngredient(List<String> ingredients); // 5th exercise
    int deleteIngredientByName(String name); // 9th exercise
    int updateAllIngredientsPrices(double percent); // 10th exercise
    int updateAllIngredientsPricesForGivenNames(double percent, List<String> names); // 11th exercise
}
