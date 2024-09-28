package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.service.impl;

import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.data.entities.Category;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.data.repositories.CategoryRepository;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String FILE_PATH = "src/main/resources/files/categories.txt";

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() == 0) {
            Files.readAllLines(Path.of(FILE_PATH))
                    .stream()
                    .filter(row -> !row.isEmpty())
                    .forEach(row -> this.categoryRepository.saveAndFlush(new Category(row)));
        }
    }

    @Override
    public Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();

        int randomCount = ThreadLocalRandom.current().nextInt(1, 4);
        for (int i = 0; i < randomCount; i++) {
            long randomId = ThreadLocalRandom.current().nextLong(1, this.categoryRepository.count() + 1);
            categories.add(this.categoryRepository.findById(randomId).get());
        }


        return categories;
    }
}
