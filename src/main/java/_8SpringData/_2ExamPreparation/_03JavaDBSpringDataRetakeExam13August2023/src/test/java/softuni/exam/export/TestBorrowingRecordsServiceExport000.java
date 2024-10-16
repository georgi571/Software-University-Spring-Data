package softuni.exam.export;
//TestBorrowingRecordsServiceExport000

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import softuni.exam.service.impl.BorrowingRecordsServiceImpl;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestBorrowingRecordsServiceExport000 {

    @Autowired
    private BorrowingRecordsServiceImpl borrowingRecordsService;

    @Sql({"/books-import.sql","/library-members-import.sql","/borrowing-record-import.sql"})
    @Test
    void testBorrowingRecordsServiceExport000() {
        String actual = borrowingRecordsService.exportBorrowingRecords();

        String expected = "Book title: The Hitchhiker's Guide to the Galaxy\n" +
                "*Book author: Douglas Adams\n" +
                "**Date borrowed: 2021-06-05\n" +
                "***Borrowed by: John Doe\n" +
                "Book title: The Lord of the Rings\n" +
                "*Book author: J.R.R. Tolkien\n" +
                "**Date borrowed: 2020-10-08\n" +
                "***Borrowed by: Sophie Müller\n" +
                "Book title: 1984\n" +
                "*Book author: George Orwell\n" +
                "**Date borrowed: 2020-09-15\n" +
                "***Borrowed by: Elizabeth Green\n" +
                "Book title: The Lord of the Rings\n" +
                "*Book author: J.R.R. Tolkien\n" +
                "**Date borrowed: 2020-08-24\n" +
                "***Borrowed by: Mia Schneider\n" +
                "Book title: The Lord of the Rings\n" +
                "*Book author: J.R.R. Tolkien\n" +
                "**Date borrowed: 2020-08-03\n" +
                "***Borrowed by: Elias Wolf\n" +
                "Book title: Brave New World\n" +
                "*Book author: Aldous Huxley\n" +
                "**Date borrowed: 2020-06-02\n" +
                "***Borrowed by: Jessica Thomas\n" +
                "Book title: 1984\n" +
                "*Book author: George Orwell\n" +
                "**Date borrowed: 2020-05-19\n" +
                "***Borrowed by: Matthew Miller\n" +
                "Book title: 1984\n" +
                "*Book author: George Orwell\n" +
                "**Date borrowed: 2020-03-11\n" +
                "***Borrowed by: Jessica Thomas\n" +
                "Book title: The Lord of the Rings\n" +
                "*Book author: J.R.R. Tolkien\n" +
                "**Date borrowed: 2020-01-13\n" +
                "***Borrowed by: Mia Schneider\n" +
                "Book title: Brave New World\n" +
                "*Book author: Aldous Huxley\n" +
                "**Date borrowed: 2019-11-30\n" +
                "***Borrowed by: Laura Garcia\n" +
                "Book title: The Lord of the Rings\n" +
                "*Book author: J.R.R. Tolkien\n" +
                "**Date borrowed: 2019-11-24\n" +
                "***Borrowed by: Elias Wolf\n" +
                "Book title: 1984\n" +
                "*Book author: George Orwell\n" +
                "**Date borrowed: 2019-11-04\n" +
                "***Borrowed by: Andrew Adams\n" +
                "Book title: The Lord of the Rings\n" +
                "*Book author: J.R.R. Tolkien\n" +
                "**Date borrowed: 2019-09-27\n" +
                "***Borrowed by: Oliver Schmidt\n" +
                "Book title: The Lord of the Rings\n" +
                "*Book author: J.R.R. Tolkien\n" +
                "**Date borrowed: 2019-09-10\n" +
                "***Borrowed by: John Doe\n" +
                "Book title: 1984\n" +
                "*Book author: George Orwell\n" +
                "**Date borrowed: 2019-08-09\n" +
                "***Borrowed by: Emma Lehmann\n";

        String[] actualSplit = actual.split("\\r\\n?|\\n");
        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        Assertions.assertArrayEquals(expectedSplit,actualSplit);
    }

}
