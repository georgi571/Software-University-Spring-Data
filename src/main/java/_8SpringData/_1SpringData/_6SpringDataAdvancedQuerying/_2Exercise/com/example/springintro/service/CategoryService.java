package _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.service;


import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.model.entity.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;

    Set<Category> getRandomCategories();
}
