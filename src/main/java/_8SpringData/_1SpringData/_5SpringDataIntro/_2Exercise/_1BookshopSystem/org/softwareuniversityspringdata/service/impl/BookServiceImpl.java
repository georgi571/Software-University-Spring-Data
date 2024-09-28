package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.service.impl;

import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.data.entities.Author;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.data.entities.Book;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.data.entities.Category;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.data.entities.enums.AgeRestriction;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.data.entities.enums.EditionType;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.data.repositories.BookRepository;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.service.AuthorService;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.service.BookService;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private static final String FILE_PATH = "src/main/resources/files/books.txt";

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }


    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() == 0) {
            Files.readAllLines(Path.of(FILE_PATH))
                    .stream()
                    .filter(row -> !row.isEmpty())
                    .forEach(row -> {
                        String[] tokens = row.split("\\s+");
                        Author author = this.authorService.getRandomAuthor();
                        EditionType editionType = EditionType.values()[Integer.parseInt(tokens[0])];
                        LocalDate releaseDate = LocalDate.parse(tokens[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
                        int copies = Integer.parseInt(tokens[2]);
                        BigDecimal price = new BigDecimal(tokens[3]);
                        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(tokens[4])];
                        String title = Arrays.stream(tokens).skip(5).collect(Collectors.joining(" "));
                        Set<Category> categories = this.categoryService.getRandomCategories();

                        Book book = new Book(title, editionType, price, copies, releaseDate, ageRestriction, author, categories);

                        bookRepository.saveAndFlush(book);
                    });
        }
    }

    @Override
    public List<String> findAllBooksAfterYear(int yearForAfter) {
        return this.bookRepository.findAllByReleaseDateAfter(LocalDate.of(yearForAfter, 12, 31))
                .stream()
                .map(book -> String.format("Title: %s", book.getTitle()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorNameOrdered(String firstName, String lastName) {
        return this.bookRepository.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("Book title: %s, release date %s, with %d copies", book.getTitle(), book.getReleaseDate(), book.getCopies()))
                .collect(Collectors.toList());
    }
}
