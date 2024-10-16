package softuni.exam.import_Borrowing_record;
//TestImportBorrowingRecordsZeroTest000

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import softuni.exam.service.impl.BorrowingRecordsServiceImpl;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportBorrowingRecordsZeroTest000 {

    @Autowired
    private BorrowingRecordsServiceImpl borrowingRecordsService;

    @Sql({"/books-import.sql", "/library-members-import.sql"})
    @Test
    void testImportBorrowingRecordsZeroTest000() throws JAXBException, IOException {
        String expected = "Successfully imported borrowing record The Lord of the Rings - 2020-01-13\n" +
                "Invalid borrowing record\n" +
                "Invalid borrowing record\n" +
                "Successfully imported borrowing record The Great Gatsby - 2020-06-01\n" +
                "Invalid borrowing record\n" +
                "Invalid borrowing record\n" +
                "Successfully imported borrowing record The Chronicles of Narnia - 2020-09-05\n" +
                "Successfully imported borrowing record The Catcher in the Rye - 2019-10-14\n" +
                "Successfully imported borrowing record The Catcher in the Rye - 2020-01-13\n" +
                "Successfully imported borrowing record The Chronicles of Narnia - 2020-10-03\n" +
                "Successfully imported borrowing record The Hobbit - 2019-11-16\n" +
                "Successfully imported borrowing record The Chronicles of Narnia - 2019-12-16\n" +
                "Successfully imported borrowing record The Chronicles of Narnia - 2019-12-20\n" +
                "Successfully imported borrowing record 1984 - 2020-03-11\n" +
                "Successfully imported borrowing record 1984 - 2020-09-15\n" +
                "Successfully imported borrowing record 1984 - 2019-08-09\n" +
                "Successfully imported borrowing record 1984 - 2020-05-19\n" +
                "Successfully imported borrowing record The Hobbit - 2019-11-16\n" +
                "Successfully imported borrowing record The Hobbit - 2019-09-26\n" +
                "Successfully imported borrowing record The Lord of the Rings - 2019-09-27\n" +
                "Successfully imported borrowing record The Great Gatsby - 2019-08-28\n" +
                "Successfully imported borrowing record The Hobbit - 2021-06-14\n" +
                "Successfully imported borrowing record The Chronicles of Narnia - 2020-08-15\n" +
                "Successfully imported borrowing record Harry Potter and the Sorcerer's Stone - 2020-11-10\n" +
                "Successfully imported borrowing record The Lord of the Rings - 2020-08-03\n" +
                "Successfully imported borrowing record The Hobbit - 2020-12-13\n" +
                "Successfully imported borrowing record The Hobbit - 2021-01-26\n" +
                "Successfully imported borrowing record Brave New World - 2020-06-02\n" +
                "Successfully imported borrowing record The Great Gatsby - 2020-11-08\n" +
                "Successfully imported borrowing record The Great Gatsby - 2020-02-11\n" +
                "Successfully imported borrowing record Brave New World - 2019-11-30\n" +
                "Successfully imported borrowing record Harry Potter and the Sorcerer's Stone - 2019-11-28\n" +
                "Successfully imported borrowing record Harry Potter and the Sorcerer's Stone - 2020-05-27\n" +
                "Successfully imported borrowing record The Great Gatsby - 2021-02-22\n" +
                "Successfully imported borrowing record The Chronicles of Narnia - 2020-09-16\n" +
                "Successfully imported borrowing record The Lord of the Rings - 2020-10-08\n" +
                "Successfully imported borrowing record The Catcher in the Rye - 2020-06-16\n" +
                "Successfully imported borrowing record Harry Potter and the Sorcerer's Stone - 2020-11-24\n" +
                "Successfully imported borrowing record Harry Potter and the Sorcerer's Stone - 2021-03-28\n" +
                "Successfully imported borrowing record The Lord of the Rings - 2019-11-24\n" +
                "Successfully imported borrowing record To Kill a Mockingbird - 2019-11-04\n" +
                "Successfully imported borrowing record The Catcher in the Rye - 2020-06-22\n" +
                "Successfully imported borrowing record The Catcher in the Rye - 2021-01-12\n" +
                "Successfully imported borrowing record The Chronicles of Narnia - 2019-08-29\n" +
                "Successfully imported borrowing record The Hitchhiker's Guide to the Galaxy - 2021-06-05\n" +
                "Successfully imported borrowing record The Lord of the Rings - 2020-08-24\n" +
                "Successfully imported borrowing record Harry Potter and the Sorcerer's Stone - 2020-07-10\n" +
                "Successfully imported borrowing record The Great Gatsby - 2020-12-06\n" +
                "Successfully imported borrowing record 1984 - 2019-11-04\n" +
                "Successfully imported borrowing record The Catcher in the Rye - 2021-02-24\n" +
                "Successfully imported borrowing record The Lord of the Rings - 2019-09-10\n" +
                "Successfully imported borrowing record Harry Potter and the Sorcerer's Stone - 2020-07-05";
        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        String actual = borrowingRecordsService.importBorrowingRecords();
        String[] actualSplit = actual.split("\\r\\n?|\\n");

        Assertions.assertArrayEquals(expectedSplit, actualSplit);
    }
}

