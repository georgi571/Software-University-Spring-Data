package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.models.entity.Genre;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

    @Query("SELECT br FROM BorrowingRecord br WHERE br.borrowDate < :borrowDate AND br.book.genre = :genre ORDER BY br.borrowDate DESC")
    List<BorrowingRecord> findAllByBorrowDateBeforeAndGenreIsOrderByBorrowDateDesc( LocalDate borrowDate, Genre genre);

}
