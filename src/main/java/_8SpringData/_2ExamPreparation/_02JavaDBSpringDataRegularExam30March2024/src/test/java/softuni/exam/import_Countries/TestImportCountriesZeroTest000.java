package softuni.exam.import_Countries;
//TestImportCountriesZeroTest000

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import softuni.exam.service.impl.CountryServiceImpl;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportCountriesZeroTest000 {
    @Autowired
    private CountryServiceImpl countryService;



    @Test
    void importCountriesZeroTest() throws IOException {
        String actual = countryService.importCountries();

        String expected = "Successfully imported country Italy - Rome\n" +
                "Invalid country\n" +
                "Successfully imported country Hawaii - Honolulu\n" +
                "Successfully imported country Japan - Tokyo\n" +
                "Successfully imported country USA - Washington\n" +
                "Successfully imported country Kabardino-Balkarian Republic - Nalchik\n" +
                "Successfully imported country Mexico - Mexico City\n" +
                "Successfully imported country Iceland - Reykjavik\n" +
                "Successfully imported country Russia - Moscow\n" +
                "Successfully imported country Tanzania - Dodoma\n" +
                "Successfully imported country Ross Island - null\n" +
                "Successfully imported country Indonesia - Jakarta\n" +
                "Successfully imported country Guatemala - Guatemala City\n" +
                "Successfully imported country Ecuador - Quito\n" +
                "Successfully imported country Martinique - Fort-de-France\n";


        String[] actualSplit = actual.split("\\r\\n?|\\n");
        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        Assertions.assertArrayEquals(expectedSplit,actualSplit);
    }
}
