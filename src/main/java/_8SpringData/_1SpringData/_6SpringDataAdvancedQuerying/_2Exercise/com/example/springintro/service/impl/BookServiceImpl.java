package _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.service.impl;

import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.model.entity.*;
import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.repository.BookInfo;
import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.repository.BookRepository;
import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.service.AuthorService;
import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.service.BookService;
import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.service.CategoryService;
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

    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";

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
        if (bookRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository
                .findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        return bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
       return bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    // 1st exercise
    @Override
    public List<String> getAllBooksByAgeRestriction(AgeRestriction ageRestriction) {
        return this.bookRepository.findAllByAgeRestriction(ageRestriction)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    // 2nd exercise
    @Override
    public List<String> getAllBooksByEditionAndCopiesLessThan(EditionType editionType, int numberOfCopies) {
        return this.bookRepository.findAllByEditionTypeAndCopiesLessThan(editionType, numberOfCopies)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    // 3rd exercise
    @Override
    public List<String> getAllBooksWithPriceLesserThanOrHigherThan(BigDecimal priceLesser, BigDecimal priceHigher) {
        return this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(priceLesser, priceHigher)
                .stream()
                .map(book -> String.format("%s - $%.2f",book.getTitle(), book.getPrice()))
                .collect(Collectors.toList());
    }

    // 4th exercise
    @Override
    public List<String> getAllBooksWithYearOfReleaseIsDifferentThan(int year) {
        return this.bookRepository.findAllByReleaseDateLessThanOrReleaseDateGreaterThan(
                LocalDate.of(year, 1, 1), LocalDate.of(year, 12, 31))
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    // 5th exercise
    @Override
    public List<String> getAllBooksReleasedBeforeDate(LocalDate localDate) {
        return this.bookRepository.findAllByReleaseDateLessThan(localDate)
                .stream()
                .map(book -> String.format("%s %s %.2f", book.getTitle(), book.getEditionType(), book.getPrice()))
                .collect(Collectors.toList());
    }

    // 7th exercise
    @Override
    public List<String> getAllBooksWhichContainingGivenLetters(String letters) {
        return this.bookRepository.findAllByTitleContains(letters)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    // 8th exercise
    @Override
    public List<String> getAllBooksWhichAuthorLastNameStartWithLetters(String letters) {
        return this.bookRepository.findAllByAuthorLastNameStartingWith(letters)
                .stream()
                .map(book -> String.format("%s (%s %s)", book.getTitle(), book.getAuthor().getFirstName(), book.getAuthor().getLastName()))
                .collect(Collectors.toList());
    }

    // 9th exercise
    @Override
    public int getNumberOfBooksWhoseTitleIsLongerThanGivenNumber(int number) {
        return this.bookRepository.countAllByTitleLengthLongerThan(number);
    }

    // 11th exercise
    @Override
    public String getInformationAboutBookByGivenTitle(String title) {
        BookInfo bookInfo = this.bookRepository.findByTitle(title);
        return String.format("%s %s %s %.2f", bookInfo.getTitle(), bookInfo.getEditionType(), bookInfo.getAgeRestriction(), bookInfo.getPrice());
    }

    // 12th exercise
    @Override
    public int updateNumberOfCopiesForBooksReleasedAfterDate(LocalDate localDate, int copies) {
        int numberOfBooks = this.bookRepository.updateBookCopiesByReleaseDateAfter(localDate, copies);
        return numberOfBooks * copies;
    }

    //13th exercise
    @Override
    public int deleteAllBooksWithCopiesLowerThan(int copies) {
        return this.bookRepository.deleteByCopiesLessThan(copies);
    }

    //14th exercise
    @Override
    public String getAuthorAndHowManyBooksWrite(String authorFirstName, String authorLastName) {
        int numberOfBooks = this.bookRepository.callProcedureWhatReturnAuthorBooksCount(authorFirstName, authorLastName);
        return String.format("%s %s has written %s books", authorFirstName, authorLastName, numberOfBooks);
    }


    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);

    }
}
