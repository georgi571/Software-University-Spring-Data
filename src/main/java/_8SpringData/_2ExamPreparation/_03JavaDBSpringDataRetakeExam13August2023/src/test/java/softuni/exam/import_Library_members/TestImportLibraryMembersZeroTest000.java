package softuni.exam.import_Library_members;
//TestImportLibraryMembersZeroTest000

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import softuni.exam.service.impl.LibraryMemberServiceImpl;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportLibraryMembersZeroTest000 {

    @Autowired
    private LibraryMemberServiceImpl libraryMemberService;

    @Test
    @Sql("/books-import.sql")
    void importLibraryMembersZeroTest() throws IOException {

        String expected = "Successfully imported library member John - Doe\n" +
                "Invalid library member\n" +
                "Invalid library member\n" +
                "Successfully imported library member Jane - Smith\n" +
                "Successfully imported library member Michael - Johnson\n" +
                "Invalid library member\n" +
                "Successfully imported library member David - Brown\n" +
                "Successfully imported library member Sarah - Jones\n" +
                "Successfully imported library member Matthew - Miller\n" +
                "Successfully imported library member Jennifer - Taylor\n" +
                "Successfully imported library member Daniel - Anderson\n" +
                "Successfully imported library member Jessica - Thomas\n" +
                "Successfully imported library member Christopher - Lee\n" +
                "Successfully imported library member Laura - Garcia\n" +
                "Successfully imported library member James - Martinez\n" +
                "Successfully imported library member Amanda - Robinson\n" +
                "Successfully imported library member Robert - Clark\n" +
                "Successfully imported library member Melissa - Lewis\n" +
                "Successfully imported library member William - Hill\n" +
                "Successfully imported library member Michelle - Lopez\n" +
                "Successfully imported library member Elizabeth - Green\n" +
                "Successfully imported library member Andrew - Adams\n" +
                "Successfully imported library member Oliver - Schmidt\n" +
                "Successfully imported library member Sophie - Müller\n" +
                "Successfully imported library member Lukas - Fischer\n" +
                "Successfully imported library member Hannah - Weber\n" +
                "Successfully imported library member Finn - Koch\n" +
                "Successfully imported library member Emma - Lehmann\n" +
                "Successfully imported library member Noah - Huber\n" +
                "Successfully imported library member Mia - Schneider\n" +
                "Successfully imported library member Elias - Wolf";

        String[] expectedSplit = expected.split("\\r\\n?|\\n");
        String actual = libraryMemberService.importLibraryMembers();
        String[] actualSplit = actual.split("\\r\\n?|\\n");

        Assertions.assertArrayEquals(expectedSplit, actualSplit);
    }


}
