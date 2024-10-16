package softuni.exam.import_Books;
//TestImportBookDuplicateEntries001

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import softuni.exam.service.impl.BookServiceImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportBookDuplicateEntries001 {

    @Autowired
    private BookServiceImpl bookService;


    @Test
    void testImportBookDuplicateTitle001() throws IOException {
        String expected = "Successfully imported book Aldous Huxley - Brave New World\n" +
                "Successfully imported book C.S. Lewis - The Chronicles of Narnia\n" +
                "Invalid book";
        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        copyRewriteFileForTest();

        try {
            String actual = bookService.importBooks();
            String[] actualSplit = actual.split("\\r\\n?|\\n");
            Assertions.assertArrayEquals(expectedSplit, actualSplit);
        } finally {
            returnOriginalValue();
        }
    }

    private void copyRewriteFileForTest() throws IOException {
        File originalJsonFile = new File("src/main/resources/files/json/books.json");

        String testJSON = "[\n" +
                "  {\n" +
                "    \"author\": \"Aldous Huxley\",\n" +
                "    \"available\": true,\n" +
                "    \"description\": \"A dystopian novel envisioning a future society\",\n" +
                "    \"genre\": \"SCIENCE_FICTION\",\n" +
                "    \"title\": \"Brave New World\",\n" +
                "    \"rating\": 4.7\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"C.S. Lewis\",\n" +
                "    \"available\": true,\n" +
                "    \"description\": \"A series of fantasy novels about Narnia\",\n" +
                "    \"genre\": \"FANTASY\",\n" +
                "    \"title\": \"The Chronicles of Narnia\",\n" +
                "    \"rating\": 5.6\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"John Harper\",\n" +
                "    \"available\": true,\n" +
                "    \"description\": \"X series of adventures and comedy\",\n" +
                "    \"genre\": \"FANTASY\",\n" +
                "    \"title\": \"Brave New World\",\n" +
                "    \"rating\": 5.6\n" +
                "  }\n" +
                "]\n";

        try {
            FileWriter f2 = new FileWriter(originalJsonFile, false);
            f2.write(testJSON);
            f2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void returnOriginalValue() {
        File originalJsonFileSrc = new File("src/main/resources/files/json/books.json");

        try {
            FileWriter f2 = new FileWriter(originalJsonFileSrc, false);
            String testOriginalFile = Files.readString(Path.of("src/test/resources/original-files/books.json"));
            f2.write(testOriginalFile);
            f2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
