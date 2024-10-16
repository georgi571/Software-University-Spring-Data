package softuni.exam.import_Library_members;
//TestImportLibraryMemberFirstNameSize002

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
public class TestImportLibraryMemberFirstNameSize002 {

    @Autowired
    private LibraryMemberServiceImpl libraryMemberService;

    @Test
    @Sql("/books-import.sql")
    void   testImportLibraryMemberFirstNameSize002() throws IOException {

        copyRewriteFileForTest();

        String expected = "Successfully imported library member Ev - Lehmann\n" +
                "Successfully imported library member Noah ist Von Den Langen Namens - Huber\n" +
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
                "    \"firstName\": \"Ev\",\n" +
                "    \"lastName\": \"Lehmann\",\n" +
                "    \"phoneNumber\": \"555-8080\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"Mühlenweg 3\",\n" +
                "    \"firstName\": \"Noah ist Von Den Langen Namens\",\n" +
                "    \"lastName\": \"Huber\",\n" +
                "    \"phoneNumber\": \"555-9090\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"Rosenweg 12\",\n" +
                "    \"firstName\": \"X\",\n" +
                "    \"lastName\": \"Schneider\",\n" +
                "    \"phoneNumber\": \"555-0101\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"Wiesenstraße 18\",\n" +
                "    \"firstName\": \"Noah ist Von Den Langen NamensX\",\n" +
                "    \"lastName\": \"Wolf\",\n" +
                "    \"phoneNumber\": \"555-1212\"\n" +
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
