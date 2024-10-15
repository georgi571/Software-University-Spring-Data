package softuni.exam.import_Volcanologists;
//TestImportVolcanologistsFirstAndLastNameSize003

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import softuni.exam.service.impl.VolcanologistServiceImpl;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportVolcanologistsFirstNameAndLastSize003 {

    @Autowired
    private VolcanologistServiceImpl astronomerService;


    @Sql({"/countries-test-imports.sql", "/volcanoes-test-imports.sql"})
    @Test
    void importVolcanologistsValidateFirstAndLastNameSize003() throws IOException, JAXBException {

        rewriteFileForTest();
        String actual = astronomerService.importVolcanologists();
        String[] actualSplit = actual.split("\\r\\n?|\\n");

        String expected = "Successfully imported volcanologist John Doe\n" +
                "Invalid volcanologist\n" +
                "Successfully imported volcanologist Stamat Stamatov\n" +
                "Invalid volcanologist\n";

        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        returnOriginalValue();
        Assertions.assertArrayEquals(expectedSplit, actualSplit);
    }
    private void rewriteFileForTest() {
        File originalJsonFile = getOriginalFile();

        String testXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<volcanologists>\n" +
                "    <volcanologist>\n" +
                "        <first_name>John</first_name>\n" +
                "        <last_name>Doe</last_name>\n" +
                "        <salary>5000.00</salary>\n" +
                "        <age>55</age>\n" +
                "        <exploring_from>1987-01-15</exploring_from>\n" +
                "        <exploring_volcano_id>11</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>J</first_name>\n" +
                "        <last_name>Efremov</last_name>\n" +
                "        <salary>5000.00</salary>\n" +
                "        <age>55</age>\n" +
                "        <exploring_from>1987-01-15</exploring_from>\n" +
                "        <exploring_volcano_id>111</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Stamat</first_name>\n" +
                "        <last_name>Stamatov</last_name>\n" +
                "        <salary>5000.00</salary>\n" +
                "        <age>55</age>\n" +
                "        <exploring_from>1987-01-15</exploring_from>\n" +
                "        <exploring_volcano_id>11</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Stamat</first_name>\n" +
                "        <last_name>E</last_name>\n" +
                "        <salary>5000.00</salary>\n" +
                "        <age>55</age>\n" +
                "        <exploring_from>1987-01-15</exploring_from>\n" +
                "        <exploring_volcano_id>111</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "</volcanologists>\n";

        try {
            FileWriter f2 = new FileWriter(originalJsonFile, false);
            f2.write(testXML);
            f2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getOriginalFile() {
        return new File("src/main/resources/files/xml/volcanologists.xml");
    }

    private void returnOriginalValue() {

        try {
            FileWriter f2 = new FileWriter(getOriginalFile(), false);
            String testOriginalFile = Files.readString(Path.of("src/test/resources/original-files/volcanologists.xml"));
            f2.write(testOriginalFile);
            f2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
