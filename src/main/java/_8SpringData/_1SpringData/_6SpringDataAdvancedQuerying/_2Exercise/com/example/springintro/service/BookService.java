package _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.service;


import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.model.entity.AgeRestriction;
import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.model.entity.Book;
import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);


    List<String> getAllBooksByAgeRestriction(AgeRestriction ageRestriction); // 1st exercise
    List<String> getAllBooksByEditionAndCopiesLessThan(EditionType editionType, int numberOfCopies); // 2nd exercise

    List<String> getAllBooksWithPriceLesserThanOrHigherThan(BigDecimal priceLesser, BigDecimal priceHigher); // 3rd exercise

    List<String> getAllBooksWithYearOfReleaseIsDifferentThan(int year); // 4th exercise

    List<String> getAllBooksReleasedBeforeDate(LocalDate localDate); // 5th exercise

    List<String> getAllBooksWhichContainingGivenLetters(String letters); // 7th exercise

    List<String> getAllBooksWhichAuthorLastNameStartWithLetters(String letters); // 8th exercise

    int getNumberOfBooksWhoseTitleIsLongerThanGivenNumber(int number); // 9th exercise

    String getInformationAboutBookByGivenTitle(String title); // 11th exercise

    int updateNumberOfCopiesForBooksReleasedAfterDate(LocalDate localDate, int copies); // 12th exercise

    int deleteAllBooksWithCopiesLowerThan(int copies); // 13th exercise

    String getAuthorAndHowManyBooksWrite(String authorFirstName, String authorLastName); // 14th exercise
}
