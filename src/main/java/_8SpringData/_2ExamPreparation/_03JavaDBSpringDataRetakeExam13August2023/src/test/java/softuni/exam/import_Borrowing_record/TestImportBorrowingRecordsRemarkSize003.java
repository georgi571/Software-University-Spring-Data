package softuni.exam.import_Borrowing_record;
//TestImportBorrowingRecordsLibraryMemberWithIDNotExist002

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import softuni.exam.service.impl.BorrowingRecordsServiceImpl;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportBorrowingRecordsRemarkSize003 {

    @Autowired
    private BorrowingRecordsServiceImpl borrowingRecordsService;


    @Sql({"/books-import.sql", "/library-members-import.sql"})
    @Test
    void testImportBorrowingRecordsLibraryMemberWithIDNotExist002() throws JAXBException,IOException {

        rewriteFileForTest();

        String expected = "Successfully imported borrowing record 1984 - 2019-11-04\n" +
                "Successfully imported borrowing record The Catcher in the Rye - 2021-02-24\n" +
                "Invalid borrowing record\n" +
                "Invalid borrowing record";
        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        try {
            String actual = borrowingRecordsService.importBorrowingRecords();
            String[] actualSplit = actual.split("\\r\\n?|\\n");
            Assertions.assertArrayEquals(expectedSplit, actualSplit);
        } finally {
            returnOriginalValue();
        }
    }

    private void rewriteFileForTest() {
        File originalJsonFile = getOriginalFile();

        String testXML = "<?xml version='1.0' encoding='UTF-8'?>\n" +
                "<borrowing_records>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2019-11-04</borrow_date>\n" +
                "        <return_date>2023-06-02</return_date>\n" +
                "        <book>\n" +
                "            <title>1984</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>19</id>\n" +
                "        </member>\n" +
                "        <remarks>Rev</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2021-02-24</borrow_date>\n" +
                "        <return_date>2022-08-30</return_date>\n" +
                "        <book>\n" +
                "            <title>The Catcher in the Rye</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>4</id>\n" +
                "        </member>\n" +
                "        <remarks>Life's a journey filled with surprises; embrace the unknown and savor every step of the way. And end</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2019-09-10</borrow_date>\n" +
                "        <return_date>2023-01-07</return_date>\n" +
                "        <book>\n" +
                "            <title>The Lord of the Rings</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>1</id>\n" +
                "        </member>\n" +
                "        <remarks>Xe</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-07-05</borrow_date>\n" +
                "        <return_date>2023-03-27</return_date>\n" +
                "        <book>\n" +
                "            <title>Harry Potter and the Sorcerer's Stone</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>25</id>\n" +
                "        </member>\n" +
                "        <remarks>Life's a journey filled with surprises; embrace the unknown and savor every step of the way. And endX</remarks>\n" +
                "    </borrowing_record>\n" +
                "</borrowing_records>\n";

        try {
            FileWriter f2 = new FileWriter(originalJsonFile, false);
            f2.write(testXML);
            f2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getOriginalFile() {
        return new File("src/main/resources/files/xml/borrowing-records.xml");
    }

    private void returnOriginalValue() {

        try {
            FileWriter f2 = new FileWriter(getOriginalFile(), false);
            String testOriginalFile = Files.readString(Path.of("src/test/resources/original-files/borrowing-records.xml"));
            f2.write(testOriginalFile);
            f2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
