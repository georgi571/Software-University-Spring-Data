package _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro;

import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.model.entity.AgeRestriction;
import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.model.entity.Book;
import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.model.entity.EditionType;
import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.service.AuthorService;
import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.service.BookService;
import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
        
        // 1st exercise
            // inputs:
                // miNor
                // teEN
        printBookByAgeRestriction();

        // 2nd exercise
        printGoldenEditionBooksWithLessThan5000Copies();

        // 3rd exercise
        printBooksWIthPriceLesserLesserThan5orHigherThan40();

        // 4th exercise
        printBooksNotReleaseAtTheGivenYear();

        // 5th exercise
            //inputs:
                // 12-04-1992
                // 30-12-1989
        printBooksReleasedBeforeDate();

        // 6th exercise
            //inputs:
                // e
                // dy
        printNameOFAuthorsWhichFirstNameEndWithGivenLetters();

        // 7th exercise
            // inputs
                //sK
                //WOR
        printBookTitleWhichContainingGivingLetters();

        // 8th exercise
            //inputs:
                //Ric
                //gr
        printBookTitlesWhichAuthorLastNameStartWithLetters();

        // 9th exercise
            //inputs:
                // 12
                // 40
        printNumberOfBooksWhoseTitleIsLongerThanGivenNumber();

        // 10th exercise
            //inputs:
                // Randy Graham
        printTotalBookCopiesByGivenAuthor();

        // 11th exercise
            //inputs:
                // Things Fall Apart
        printInformationAboutBookByGivenTitle();

        // 12th exercise
            //inputs:
                //12 Oct 2005
                //100
                //06 Jun 2013
                //44
        printNumberOfAddedBookCopies();

        // 13th exercise
            //inputs:
                //300
        printDeletedBooksWithCopiesLowerThan();

        // 14th exercise
            //inputs:
                // Amanda Rice
                // Christina Jordan
                // Wanda Morales
        printAuthorAndHowManyBooksWrite();



        // from resource
        printAllBooksAfterYear(2000);
        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
        printAllAuthorsAndNumberOfTheirBooks();
        printALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");

    }

    private void printAuthorAndHowManyBooksWrite() {
        Scanner scanner = new Scanner(System.in);
        String[] author = scanner.nextLine().split("\\s+");
        String authorFirstName = author[0];
        String authorLastName = author[1];
        String information = this.bookService.getAuthorAndHowManyBooksWrite(authorFirstName, authorLastName);
        System.out.printf("%s%n", information);
    }

    private void printDeletedBooksWithCopiesLowerThan() {
        Scanner scanner = new Scanner(System.in);
        int copies = Integer.parseInt(scanner.nextLine());
        int numberOfBooks = this.bookService.deleteAllBooksWithCopiesLowerThan(copies);
        System.out.printf("%d%n", numberOfBooks);
    }

    private void printNumberOfAddedBookCopies() {
        Scanner scanner = new Scanner(System.in);
        LocalDate localDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd MMM yyyy"));
        int copies = Integer.parseInt(scanner.nextLine());
        int numberOfCopies = this.bookService.updateNumberOfCopiesForBooksReleasedAfterDate(localDate, copies);
        System.out.printf("%d%n", numberOfCopies);
    }

    private void printInformationAboutBookByGivenTitle() {
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        String information = this.bookService.getInformationAboutBookByGivenTitle(title);
        System.out.printf("%s%n", information);
    }

    private void printTotalBookCopiesByGivenAuthor() {
        Scanner scanner = new Scanner(System.in);
        String[] authorName = scanner.nextLine().split("\\s+");
        String firstName = authorName[0];
        String lastName = authorName[1];
        int numberOfCopies = this.authorService.getTotalBookCopiesByGivenAuthorFirstAndLastName(firstName, lastName);
        System.out.printf("%s %s - %d%n", firstName, lastName, numberOfCopies);
    }

    private void printNumberOfBooksWhoseTitleIsLongerThanGivenNumber() {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.parseInt(scanner.nextLine());
        int count = this.bookService.getNumberOfBooksWhoseTitleIsLongerThanGivenNumber(number);
        System.out.printf("There are %d books with longer titles than %d symbols.%n", count, number);
    }

    private void printBookTitlesWhichAuthorLastNameStartWithLetters() {
        Scanner scanner = new Scanner(System.in);
        String letters = scanner.nextLine();
        this.bookService.getAllBooksWhichAuthorLastNameStartWithLetters(letters)
                .forEach(author -> System.out.printf("%s%n", author));
    }

    private void printBookTitleWhichContainingGivingLetters() {
        Scanner scanner = new Scanner(System.in);
        String letters = scanner.nextLine();
        this.bookService.getAllBooksWhichContainingGivenLetters(letters)
                .forEach(author -> System.out.printf("%s%n", author));
    }

    private void printNameOFAuthorsWhichFirstNameEndWithGivenLetters() {
        Scanner scanner = new Scanner(System.in);
        String letters = scanner.nextLine();
        this.authorService.getAllAuthorsWhichFirstNameEndWithGivenLetters(letters)
                .forEach(author -> System.out.printf("%s%n", author));
    }

    private void printBooksReleasedBeforeDate() {
        Scanner scanner = new Scanner(System.in);
        LocalDate localDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.bookService.getAllBooksReleasedBeforeDate(localDate)
                .forEach(book -> System.out.printf("%s%n", book));
    }

    private void printBooksNotReleaseAtTheGivenYear() {
        Scanner scanner = new Scanner(System.in);
        int year = Integer.parseInt(scanner.nextLine());
        this.bookService.getAllBooksWithYearOfReleaseIsDifferentThan(year)
                .forEach(title -> System.out.printf("%s%n", title));
    }

    private void printBooksWIthPriceLesserLesserThan5orHigherThan40() {
        this.bookService.getAllBooksWithPriceLesserThanOrHigherThan(BigDecimal.valueOf(5.00), BigDecimal.valueOf(40.00))
                .forEach(book -> System.out.printf("%s%n", book));
    }

    private void printGoldenEditionBooksWithLessThan5000Copies() {
        this.bookService.getAllBooksByEditionAndCopiesLessThan(EditionType.GOLD, 5000)
                .forEach(title -> System.out.printf("%s%n", title));
    }

    private void printBookByAgeRestriction() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        AgeRestriction ageRestriction;
        try {
            ageRestriction = AgeRestriction.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException exception) {
            System.out.printf("Wrong age category%n");
            return;
        }
        bookService.getAllBooksByAgeRestriction(ageRestriction)
                .forEach(title -> System.out.printf("%s%n", title));
    }

    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
