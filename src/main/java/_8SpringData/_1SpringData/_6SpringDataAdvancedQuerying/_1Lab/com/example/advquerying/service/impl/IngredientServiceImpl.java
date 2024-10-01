package _8SpringData._1SpringData._6SpringDataAdvancedQuerying._1Lab.com.example.advquerying.service.impl;

import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._1Lab.com.example.advquerying.entities.Ingredient;
import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._1Lab.com.example.advquerying.repositrories.IngredientRepository;
import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._1Lab.com.example.advquerying.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    // 4th exercise
    @Override
    public List<String> getAllIngredientStartWithLetters(String letters) {
        return this.ingredientRepository.findAllByNameStartsWith(letters)
                .stream()
                .map(ingredient -> String.format("%s", ingredient.getName()))
                .collect(Collectors.toList());
    }

    //5th exercise
    @Override
    public List<String> getAllContainingIngredient(List<String> ingredients) {
        return this.ingredientRepository.findAllByNameInOrderByPrice(ingredients)
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList());
    }

    // 9th exercise
    @Override
    public int deleteIngredientByName(String name) {
        return this.ingredientRepository.deleteIngredientByName(name);
    }

    // 10th exercise
    @Override
    public int updateAllIngredientsPrices(double percent) {
        double percentForUpdate = 1 + (percent / 100);
        return this.ingredientRepository.updateAllByPrice(percentForUpdate);
    }

    // 11th exercise
    @Override
    public int updateAllIngredientsPricesForGivenNames(double percent, List<String> names) {
        double percentForUpdate = 1 + (percent / 100);
        return this.ingredientRepository.updateAllByPriceForGivenName(percentForUpdate, names);
    }
}
