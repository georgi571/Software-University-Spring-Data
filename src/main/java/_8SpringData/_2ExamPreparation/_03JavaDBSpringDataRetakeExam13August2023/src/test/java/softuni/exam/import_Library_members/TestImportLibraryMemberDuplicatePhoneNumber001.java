package softuni.exam.import_Library_members;
//TestImportLibraryMemberDuplicatePhoneNumber001

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
public class TestImportLibraryMemberDuplicatePhoneNumber001 {

    @Autowired
    private LibraryMemberServiceImpl libraryMemberService;

    @Test
    @Sql("/books-import.sql")
    void   testImportLibraryMemberDuplicatePhoneNumber001() throws IOException {

        copyRewriteFileForTest();

        String expected = "Successfully imported library member John - Doe\n" +
                "Invalid library member";
        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        try {
            String actual = libraryMemberService.importLibraryMembers();
            String[] actualSplit = actual.split("\\r\\n?|\\n");
            Assertions.assertArrayEquals(expectedSplit, actualSplit);
        } finally {
            returnOriginalValue();
        }
    }

    private void copyRewriteFileForTest() {

        String testJSON = "[\n" +
                "  {\n" +
                "    \"address\": \"123 Main St\",\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Doe\",\n" +
                "    \"phoneNumber\": \"555-1111\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"13 Main St\",\n" +
                "    \"firstName\": \"Joah\",\n" +
                "    \"lastName\": \"Doevill\",\n" +
                "    \"phoneNumber\": \"555-1111\"\n" +
                "  } \n" +
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
