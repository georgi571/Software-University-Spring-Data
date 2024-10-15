package softuni.exam.import_Sellers;
//TestImportSellerPersonalNumberSize002

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import softuni.exam.service.impl.SellerServiceImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportSellerPersonalNumberSize002 {

    @Autowired
    private SellerServiceImpl sellerService;

    @Test
    void importSellerPersonalNumberSize002() throws IOException {


        String expected = "Successfully imported seller John Harrison\n" +
                "Invalid seller\n" +
                "Invalid seller";
        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        copyRewriteFileForTest();
        String actual = sellerService.importSellers();

        String[] actualSplit = actual.split("\\r\\n?|\\n");

        returnOriginalValue();
        Assertions.assertArrayEquals(expectedSplit, actualSplit);
    }

    private void copyRewriteFileForTest() {
        File originalJsonFile = new File("src/main/resources/files/json/sellers.json");

        String testJSON = "[\n" +
                "  {\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Harrison\",\n" +
                "    \"personalNumber\": \"123123\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Alice\",\n" +
                "    \"lastName\": \"Smith\",\n" +
                "    \"personalNumber\": \"12\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Michael\",\n" +
                "    \"lastName\": \"Johnson\",\n" +
                "    \"personalNumber\": \"1231234\"\n" +
                "  }\n" +
                "]";

        try {
            FileWriter f2 = new FileWriter(originalJsonFile, false);
            f2.write(testJSON);
            f2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void returnOriginalValue() {
        File originalJsonFileSrc = new File("src/main/resources/files/json/sellers.json");

        try {
            FileWriter f2 = new FileWriter(originalJsonFileSrc, false);
            String testOriginalFile = Files.readString(Path.of("src/test/resources/original-files/sellers.json"));
            f2.write(testOriginalFile);
            f2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
