package softuni.exam.import_Library_members;
//TestImportLibraryMemberAddressSizeAndNull004

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
public class TestImportLibraryMemberAddressSizeAndNull004 {

    @Autowired
    private LibraryMemberServiceImpl libraryMemberService;

    @Test
    @Sql("/books-import.sql")
    void   testImportLibraryMemberAddressSizeAndNull004() throws IOException {

        copyRewriteFileForTest();

        String expected = "Successfully imported library member Hannah - Weber\n" +
                "Successfully imported library member Finn - Koch\n" +
                "Invalid library member\n" +
                "Invalid library member\n" +
                "Successfully imported library member Mia - Schneider";
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
                "\n" +
                "  {\n" +
                "    \"address\": \"Dv\",\n" +
                "    \"firstName\": \"Hannah\",\n" +
                "    \"lastName\": \"Weber\",\n" +
                "    \"phoneNumber\": \"555-6060\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"Schulweg an der Rein undMeier Raabst 10 \",\n" +
                "    \"firstName\": \"Finn\",\n" +
                "    \"lastName\": \"Koch\",\n" +
                "    \"phoneNumber\": \"555-7070\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"X\",\n" +
                "    \"firstName\": \"Emma\",\n" +
                "    \"lastName\": \"Lehmann\",\n" +
                "    \"phoneNumber\": \"555-8080\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"Schulweg an der Rein undMeier Raabst 10 X\",\n" +
                "    \"firstName\": \"Noah\",\n" +
                "    \"lastName\": \"Huber\",\n" +
                "    \"phoneNumber\": \"555-9090\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": null,\n" +
                "    \"firstName\": \"Mia\",\n" +
                "    \"lastName\": \"Schneider\",\n" +
                "    \"phoneNumber\": \"555-0101\"\n" +
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
