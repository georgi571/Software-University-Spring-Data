package softuni.exam.import_Books;
//TestImportBooksDescriptionSize004

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
public class TestImportBooksDescriptionSize004 {

    @Autowired
    private BookServiceImpl bookService;

    @Test
    void testImportBooksDescriptionSize004() throws IOException {

        String expected = "Successfully imported book Toni Morrison - Beloved\n" +
                "Invalid book\n";
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
                "    {\n" +
                "    \"author\": \"Toni Morrison\",\n" +
                "    \"available\": true,\n" +
                "    \"description\": \"Tells the story of a dysfunctional family of formerly enslaved people whose Cincinnati home is haunted by a malevolent spirit.\",\n" +
                "    \"genre\": \"CLASSIC_LITERATURE\",\n" +
                "    \"title\": \"Beloved\",\n" +
                "    \"rating\": 3.1\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"Douglas Adams\",\n" +
                "    \"available\": true,\n" +
                "    \"description\": \"Xnot\",\n" +
                "    \"genre\": \"SCIENCE_FICTION\",\n" +
                "    \"title\": \"Catch-22\",\n" +
                "    \"rating\": 4.2\n" +
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
