package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.service.impl;


import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.data.entities.Author;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.data.repositories.AuthorRepository;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.service.AuthorService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private static final String FILE_PATH = "src/main/resources/files/authors.txt";

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count() == 0) {
            Files.readAllLines(Path.of(FILE_PATH))
                    .stream()
                    .filter(row -> !row.isEmpty())
                    .forEach(row -> {
                        String[] tokens = row.split("\\s+");
                        this.authorRepository.saveAndFlush(new Author(tokens[0], tokens[1]));
                    });
        }
    }

    @Override
    public Author getRandomAuthor() {
        long randomId = ThreadLocalRandom.current().nextLong(1, this.authorRepository.count() + 1);
        return this.authorRepository.findById(randomId).get();
    }

    @Override
    public List<String> getAuthorFirstAndLastNameForBooksBeforeYear(int yearForBefore) {
        return this.authorRepository.findAllByBooksReleaseDateBefore(LocalDate.of(yearForBefore, 1, 1))
                .stream()
                .map(author -> String.format("Author name: %s %s", author.getFirstName(), author.getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllByOrderByBooksSizeDesc() {
        Set<Author> allByOrderByBooksDesc = this.authorRepository.findAllByOrderByBooksSizeDesc();

        return allByOrderByBooksDesc.stream()
                .map(author -> String.format("Author name: %s %s, number of books %d", author.getFirstName(), author.getLastName(), author.getBooks().size()))
                .collect(Collectors.toList());
    }
}
