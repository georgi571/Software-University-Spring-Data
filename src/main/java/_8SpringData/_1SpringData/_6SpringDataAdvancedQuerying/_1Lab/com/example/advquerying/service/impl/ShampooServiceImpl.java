package _8SpringData._1SpringData._6SpringDataAdvancedQuerying._1Lab.com.example.advquerying.service.impl;

import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._1Lab.com.example.advquerying.entities.Shampoo;
import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._1Lab.com.example.advquerying.entities.Size;
import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._1Lab.com.example.advquerying.repositrories.ShampooRepository;
import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._1Lab.com.example.advquerying.service.ShampooService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShampooServiceImpl implements ShampooService {
    private final ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    // 1st exercise
    @Override
    public List<String> getAllShampooByGivenSize(String size) {
        Size sizeEnum = Size.valueOf(size.toUpperCase());
        return this.shampooRepository.findAllBySizeOrderById(sizeEnum)
                .stream()
                .map(shampoo -> String.format("%s %s %.2flv.", shampoo.getBrand(), shampoo.getSize(), shampoo.getPrice().doubleValue()))
                .collect(Collectors.toList());
    }

    //2nd exercise
    @Override
    public List<String> getAllShampooByGivenSizeOrLabel(String size, long id) {
        Size sizeEnum = Size.valueOf(size.toUpperCase());
        return this.shampooRepository.findAllBySizeOrLabelIdOrderByPrice(sizeEnum, id)
                .stream()
                .map(shampoo -> String.format("%s %s %.2flv.", shampoo.getBrand(), shampoo.getSize(), shampoo.getPrice().doubleValue()))
                .collect(Collectors.toList());
    }

    //3rd exercise
    @Override
    public List<String> getAllShampooWithPriceHigherThan(BigDecimal price) {
        return this.shampooRepository.findAllByPriceGreaterThanOrderByPriceDesc(price)
                .stream()
                .map(shampoo -> String.format("%s %s %.2flv.", shampoo.getBrand(), shampoo.getSize(), shampoo.getPrice().doubleValue()))
                .collect(Collectors.toList());
    }

    // 6th exercise
    @Override
    public Long getCountOfAllShampooWithPriceLowerThan(BigDecimal price) {
        return (long) this.shampooRepository.findAllByPriceLessThan(price).size();
    }


    // 7th exercise
    @Override
    public Set<String> getAllShampoosContainingIngredient(List<String> ingredients) {
        return this.shampooRepository.findAllByIngredientsNameIn(ingredients)
                .stream()
                .map(Shampoo::getBrand)
                .collect(Collectors.toSet());
    }

    // 8th exercise
    @Override
    public Set<String> getAllShampooWithCountOfIngredientsBelowNumber(int number) {
        return this.shampooRepository.findAllWithIngredientsCountLesserThan(number)
                .stream()
                .map(Shampoo::getBrand)
                .collect(Collectors.toSet());
    }
}
