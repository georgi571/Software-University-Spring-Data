package _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.repository;

import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a ORDER BY SIZE(a.books) DESC")
    List<Author> findAllByBooksSizeDESC();

    List<Author> findAllByFirstNameEndingWith(String letters); // 6th exercise

    // 10th exercise
    @Query("SELECT SUM(b.copies) " +
            "FROM Author a " +
            "JOIN Book b ON b.author = a " +
            "WHERE a.firstName = :firstName AND a.lastName = :lastName")
    int sumAllBookCopiesByGivenName(String firstName, String lastName);
}
