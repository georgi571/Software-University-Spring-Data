package softuni.exam.import_Sale;
//TestImportSaleWithNonExistingSeller002

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import softuni.exam.service.impl.SaleServiceImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportSaleWithNonExistingSeller002 {

    @Autowired
    private SaleServiceImpl saleService;

    @Test
    @Sql("/sellers-test-imports.sql")
    void importVolcanoNameSize() throws IOException {

        String expected = "Successfully imported sale with number 1000123\n" +
                "Invalid sale";
        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        copyRewriteFileForTest();
        String actual = saleService.importSales();
        String[] actualSplit = actual.split("\\r\\n?|\\n");

        returnOriginalValue();
        Assertions.assertArrayEquals(expectedSplit, actualSplit);

    }


    private void copyRewriteFileForTest() {
        File originalJsonFile = getOriginalFile();

        String testJSON = "[\n" +
                "  {\n" +
                "    \"discounted\": \"true\",\n" +
                "    \"number\": 1000123,\n" +
                "    \"saleDate\": \"2022-02-02 12:43:00\",\n" +
                "    \"seller\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": \"true\",\n" +
                "    \"number\": 1000123,\n" +
                "    \"saleDate\": \"2022-02-02 12:43:00\",\n" +
                "    \"seller\": 21\n" +
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

    private File getOriginalFile() {
        return new File("src/main/resources/files/json/sales.json");
    }

    private void returnOriginalValue() {

        try {
            FileWriter f2 = new FileWriter(getOriginalFile(), false);
            String testOriginalFile = Files.readString(Path.of("src/test/resources/original-files/sales.json"));
            f2.write(testOriginalFile);
            f2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
