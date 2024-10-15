package softuni.exam.import_Volcano;
//TestImportVolcanoNameSize002

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import softuni.exam.service.impl.VolcanoServiceImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportVolcanoNameSize002 {

    @Autowired
    private VolcanoServiceImpl volcanoService;

    @Test
    @Sql("/countries-test-imports.sql")
    void importVolcanoNameSize() throws IOException {

        String expected = "Successfully imported volcano Mount Vesuvius of type STRATOVOLCANO\n" +
                "Invalid volcano";
        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        copyRewriteFileForTest();
        String actual = volcanoService.importVolcanoes();
        String[] actualSplit = actual.split("\\r\\n?|\\n");

        returnOriginalValue();
        Assertions.assertArrayEquals(expectedSplit, actualSplit);

    }


    private void copyRewriteFileForTest() throws IOException {
        File originalJsonFile = getOriginalFile();

        String testJSON = "[\n" +
                "  {\n" +
                "    \"name\": \"Mount Vesuvius\",\n" +
                "    \"elevation\": 1281,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": \"true\",\n" +
                "    \"lastEruption\": \"2021-01-01\",\n" +
                "    \"country\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"M\",\n" +
                "    \"elevation\": 1281,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": \"true\",\n" +
                "    \"lastEruption\": \"2021-01-01\",\n" +
                "    \"country\": 1\n" +
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
        return new File("src/main/resources/files/json/volcanoes.json");
    }

    private void returnOriginalValue() {

        try {
            FileWriter f2 = new FileWriter(getOriginalFile(), false);
            String testOriginalFile = Files.readString(Path.of("src/test/resources/original-files/volcanoes.json"));
            f2.write(testOriginalFile);
            f2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
