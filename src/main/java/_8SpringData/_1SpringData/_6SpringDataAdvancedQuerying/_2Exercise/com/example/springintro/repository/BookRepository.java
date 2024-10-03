package _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.repository;

import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.model.entity.AgeRestriction;
import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.model.entity.Book;
import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction); // 1st exercise
    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int numberOfCopies); // 2nd exercise
    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal priceLesser, BigDecimal priceHigher); // 3rd exercise
    List<Book> findAllByReleaseDateLessThanOrReleaseDateGreaterThan(LocalDate before, LocalDate after); // 4th exercise
    List<Book> findAllByReleaseDateLessThan(LocalDate before); // 5th exercise
    List<Book> findAllByTitleContains(String letters); // 7th exercise
    List<Book> findAllByAuthorLastNameStartingWith(String letters); // 8th exercise

    @Query("SELECT COUNT(b) FROM Book b WHERE LENGTH(b.title) > :min")
    int countAllByTitleLengthLongerThan(int min); // 9th exercise

    BookInfo findByTitle(String title); // 11th exercise

    @Query("UPDATE Book b SET b.copies = b.copies + :additionalCopies WHERE b.releaseDate > :after")
    @Modifying
    @Transactional
    int updateBookCopiesByReleaseDateAfter(LocalDate after, int additionalCopies); // 12th exercise

    @Query("DELETE Book b WHERE b.copies < :copies")
    @Modifying
    @Transactional
    int deleteByCopiesLessThan(int copies); // 13th exercise

    @Procedure("bookshop_system.usp_total_amount_of_books_author_write")
    int callProcedureWhatReturnAuthorBooksCount(String firstName, String lastName); //14th exercise

}
