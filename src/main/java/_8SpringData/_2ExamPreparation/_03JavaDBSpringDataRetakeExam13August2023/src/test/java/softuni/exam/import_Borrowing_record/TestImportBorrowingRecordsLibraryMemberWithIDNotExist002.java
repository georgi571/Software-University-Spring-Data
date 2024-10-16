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
public class TestImportBorrowingRecordsLibraryMemberWithIDNotExist002 {

    @Autowired
    private BorrowingRecordsServiceImpl borrowingRecordsService;


    @Sql({"/books-import.sql", "/library-members-import.sql"})
    @Test
    void testImportBorrowingRecordsLibraryMemberWithIDNotExist002() throws JAXBException,IOException {

        rewriteFileForTest();

        String expected = "Successfully imported borrowing record The Lord of the Rings - 2019-09-10\n" +
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
                "        <borrow_date>2019-09-10</borrow_date>\n" +
                "        <return_date>2023-01-07</return_date>\n" +
                "        <book>\n" +
                "            <title>The Lord of the Rings</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>1</id>\n" +
                "        </member>\n" +
                "        <remarks>Member provided student ID for verification.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-07-05</borrow_date>\n" +
                "        <return_date>2023-03-27</return_date>\n" +
                "        <book>\n" +
                "            <title>Harry Potter and the Sorcerer's Stone</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>35</id>\n" +
                "        </member>\n" +
                "        <remarks>Late return, fine applied as per library policy.</remarks>\n" +
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
