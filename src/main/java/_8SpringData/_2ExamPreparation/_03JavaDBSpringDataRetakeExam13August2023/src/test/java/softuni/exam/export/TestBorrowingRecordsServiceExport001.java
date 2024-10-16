package softuni.exam.export;
//TestBorrowingRecordsServiceExport001

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import softuni.exam.service.impl.BorrowingRecordsServiceImpl;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestBorrowingRecordsServiceExport001 {

    @Autowired
    private BorrowingRecordsServiceImpl borrowingRecordsService;

    @Sql("/export-001.sql")
    @Test
    void exportBorrowingRecords001() {
        String actual = borrowingRecordsService.exportBorrowingRecords();

        String expected = "Book title: The Lord of the Rings\n" +
                "*Book author: J.R.R. Tolkien\n" +
                "**Date borrowed: 2020-10-08\n" +
                "***Borrowed by: Sophie Müller\n" +
                "Book title: Brave New World\n" +
                "*Book author: Aldous Huxley\n" +
                "**Date borrowed: 2019-11-30\n" +
                "***Borrowed by: Ivan Doe\n" +
                "Book title: 1984\n" +
                "*Book author: George Orwell\n" +
                "**Date borrowed: 2019-11-04\n" +
                "***Borrowed by: Andrew Adams\n" +
                "Book title: The Lord of the Rings\n" +
                "*Book author: J.R.R. Tolkien\n" +
                "**Date borrowed: 2019-09-10\n" +
                "***Borrowed by: Ivan Doe\n";

        String[] actualSplit = actual.split("\\r\\n?|\\n");
        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        Assertions.assertArrayEquals(expectedSplit,actualSplit);
    }
}
