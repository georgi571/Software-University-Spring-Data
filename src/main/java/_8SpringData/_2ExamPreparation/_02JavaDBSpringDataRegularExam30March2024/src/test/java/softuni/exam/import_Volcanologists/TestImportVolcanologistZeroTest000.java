package softuni.exam.import_Volcanologists;
//TestImportVolcanologistZeroTest000

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import softuni.exam.service.impl.VolcanologistServiceImpl;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportVolcanologistZeroTest000 {

    @Autowired
    private VolcanologistServiceImpl volcanologistService;

    @Sql({"/countries-test-imports.sql", "/volcanoes-test-imports.sql"})
    @Test
    void importAstronomersZeroTest() throws IOException, JAXBException {
        String expected = "Successfully imported volcanologist John Doe\n" +
                "Successfully imported volcanologist Jane Smith\n" +
                "Successfully imported volcanologist Michael Johnson\n" +
                "Invalid volcanologist\n" +
                "Successfully imported volcanologist Emily Brown\n" +
                "Successfully imported volcanologist Daniel Martinez\n" +
                "Successfully imported volcanologist Sarah Taylor\n" +
                "Successfully imported volcanologist Matthew Anderson\n" +
                "Successfully imported volcanologist Jessica Wilson\n" +
                "Successfully imported volcanologist Christopher Thompson\n" +
                "Successfully imported volcanologist Amanda Garcia\n" +
                "Successfully imported volcanologist David Hernandez\n" +
                "Successfully imported volcanologist Ashley Lopez\n" +
                "Successfully imported volcanologist James Gonzalez\n" +
                "Successfully imported volcanologist Jennifer Rodriguez\n" +
                "Successfully imported volcanologist Ryan Perez\n" +
                "Successfully imported volcanologist Nicole Sanchez\n" +
                "Successfully imported volcanologist Kevin Ramirez\n" +
                "Successfully imported volcanologist Elizabeth Torres\n" +
                "Successfully imported volcanologist William Flores\n" +
                "Successfully imported volcanologist Megan Rivera\n" +
                "Successfully imported volcanologist Jacob Campbell\n" +
                "Successfully imported volcanologist Olivia Carter\n" +
                "Successfully imported volcanologist Liam Hill\n" +
                "Successfully imported volcanologist Emma Yung\n" +
                "Successfully imported volcanologist Mason Green\n" +
                "Successfully imported volcanologist Sophia King\n" +
                "Successfully imported volcanologist Peter Evans\n" +
                "Successfully imported volcanologist Isabella Scott\n" +
                "Successfully imported volcanologist Ethan Adams\n" +
                "Successfully imported volcanologist Amelia Baker\n" +
                "Successfully imported volcanologist Alexander Cook\n" +
                "Successfully imported volcanologist Marta Gonzales\n" +
                "Successfully imported volcanologist Samantha Nelson\n" +
                "Successfully imported volcanologist Benjamin Mitchell\n" +
                "Successfully imported volcanologist Chloe Roberts\n" +
                "Successfully imported volcanologist Dani Leen\n" +
                "Successfully imported volcanologist Ava Walker\n" +
                "Successfully imported volcanologist Madison Hall\n" +
                "Successfully imported volcanologist Patrick Lewis\n" +
                "Successfully imported volcanologist Abigail Young\n";

        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        String actual = volcanologistService.importVolcanologists();
        String[] actualSplit = actual.split("\\r\\n?|\\n");


        Assertions.assertArrayEquals(expectedSplit, actualSplit);
    }
}