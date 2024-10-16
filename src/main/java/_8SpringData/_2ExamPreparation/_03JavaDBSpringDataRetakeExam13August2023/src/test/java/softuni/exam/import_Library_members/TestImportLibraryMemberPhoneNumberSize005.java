package softuni.exam.import_Library_members;
//TestImportLibraryMemberPhoneNumberSize005

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import softuni.exam.service.impl.LibraryMemberServiceImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportLibraryMemberPhoneNumberSize005 {

    @Autowired
    private LibraryMemberServiceImpl libraryMemberService;

    @Test
    @Sql("/books-import.sql")
    void   testImportLibraryMemberPhoneNumberSize005() throws IOException {

        copyRewriteFileForTest();

        String expected = "Successfully imported library member Emma - Lehmann\n" +
                "Successfully imported library member Noah - Huber\n" +
                "Invalid library member\n" +
                "Invalid library member";
        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        try {
            String actual = libraryMemberService.importLibraryMembers();
            String[] actualSplit = actual.split("\\r\\n?|\\n");
            Assertions.assertArrayEquals(expectedSplit, actualSplit);
        }
        finally {
            returnOriginalValue();
        }
    }

    private void copyRewriteFileForTest() {

        String testJSON = "[\n" +
                "  {\n" +
                "    \"address\": \"Bergstraße 5\",\n" +
                "    \"firstName\": \"Emma\",\n" +
                "    \"lastName\": \"Lehmann\",\n" +
                "    \"phoneNumber\": \"55\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"Mühlenweg 3\",\n" +
                "    \"firstName\": \"Noah\",\n" +
                "    \"lastName\": \"Huber\",\n" +
                "    \"phoneNumber\": \"555-9090-12345-64892\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"Rosenweg 12\",\n" +
                "    \"firstName\": \"Mia\",\n" +
                "    \"lastName\": \"Schneider\",\n" +
                "    \"phoneNumber\": \"5\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"Wiesenstraße 18\",\n" +
                "    \"firstName\": \"Elias\",\n" +
                "    \"lastName\": \"Wolf\",\n" +
                "    \"phoneNumber\": \"555-9090-12345-648920\"\n" +
                "  }\n" +
                "]";

        try {
            FileWriter f2 = new FileWriter(getOriginalFile(), false);
            f2.write(testJSON);
            f2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getOriginalFile() {
        return new File("src/main/resources/files/json/library-members.json");
    }

    private void returnOriginalValue() {

        try {
            FileWriter f2 = new FileWriter(getOriginalFile(), false);
            String testOriginalFile = Files.readString(Path.of("src/test/resources/original-files/library-members.json"));
            f2.write(testOriginalFile);
            f2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
