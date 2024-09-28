package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata;

import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.service.AuthorService;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.service.BookService;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoryService categoryService;

    public CommandLineRunnerImpl(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        int yearForAfter = 2000;
        getAllTitleForBooksAfterYear(yearForAfter);

        int yearForBefore = 1990;
        getAllAuthorsFirstAndLastNameForBooksBeforeYear(yearForBefore);


        getAllAuthorsByBooksDesc();

        String firstName = "George";
        String lastName = "Powell";
        printByAuthor(firstName, lastName);
    }

    private void printByAuthor(String firstName, String lastName) {
        this.bookService.findAllBooksByAuthorNameOrdered(firstName, lastName)
                .forEach(book -> System.out.printf("%s%n", book));
    }

    private void getAllAuthorsByBooksDesc() {
        this.authorService.findAllByOrderByBooksSizeDesc()
                .forEach(author -> System.out.printf("%s%n", author));
    }

    private void getAllAuthorsFirstAndLastNameForBooksBeforeYear(int yearForBefore) {
        this.authorService.getAuthorFirstAndLastNameForBooksBeforeYear(yearForBefore)
                .forEach(author -> System.out.printf("%s%n", author));
    }

    private void getAllTitleForBooksAfterYear(int yearForAfter) {
        this.bookService.findAllBooksAfterYear(yearForAfter)
                .forEach(title -> System.out.printf("%s%n", title));
    }

    private void seedData() throws IOException {
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();
    }
}
