package softuni.exam.import_Volcano;
//TestImportStarZeroTest000

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import softuni.exam.service.impl.VolcanoServiceImpl;

import java.io.IOException;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportVolcanoZeroTest000 {

    @Autowired
    private VolcanoServiceImpl volcanoService;

    @Test
    @Sql("/countries-test-imports.sql")
    void importStarsZeroTest() throws IOException {

        String expected = "Successfully imported volcano Mount Vesuvius of type STRATOVOLCANO\n" +
                "Invalid volcano\n" +
                "Successfully imported volcano Mauna Loa of type SHIELD_VOLCANO\n" +
                "Successfully imported volcano Mount Fuji of type STRATOVOLCANO\n" +
                "Successfully imported volcano Mount St. Helens of type STRATOVOLCANO\n" +
                "Successfully imported volcano Mount Kilimanjaro of type STRATOVOLCANO\n" +
                "Successfully imported volcano Klyuchevskaya Sopka of type STRATOVOLCANO\n" +
                "Successfully imported volcano Popocatepetl of type STRATOVOLCANO\n" +
                "Successfully imported volcano Mount Elbrus of type STRATOVOLCANO\n" +
                "Successfully imported volcano Eyjafjallajokull of type STRATOVOLCANO\n" +
                "Successfully imported volcano Mount Erebus of type STRATOVOLCANO\n" +
                "Successfully imported volcano Mount Rainier of type STRATOVOLCANO\n" +
                "Successfully imported volcano Piton de la Fournaise of type SHIELD_VOLCANO\n" +
                "Successfully imported volcano Mount Etna of type STRATOVOLCANO\n" +
                "Successfully imported volcano Mount Shasta of type STRATOVOLCANO\n" +
                "Successfully imported volcano Mount Ijen of type STRATOVOLCANO\n" +
                "Successfully imported volcano Mount Rinjani of type STRATOVOLCANO\n" +
                "Successfully imported volcano Mount Merapi of type STRATOVOLCANO\n" +
                "Successfully imported volcano Mount Tambora of type STRATOVOLCANO\n" +
                "Successfully imported volcano Mount Pelee of type STRATOVOLCANO\n" +
                "Successfully imported volcano Pacaya of type STRATOVOLCANO\n" +
                "Successfully imported volcano Novarupta of type STRATOVOLCANO\n" +
                "Successfully imported volcano Cotopaxi of type STRATOVOLCANO\n" +
                "Successfully imported volcano Mount Cleveland of type STRATOVOLCANO\n" +
                "Successfully imported volcano Santa Maria of type STRATOVOLCANO\n" +
                "Successfully imported volcano Mount Aniakchak of type CALDERA\n" +
                "Successfully imported volcano Paricutin of type CINDER_CONE\n" +
                "Successfully imported volcano Mount Jefferson of type STRATOVOLCANO\n" +
                "Successfully imported volcano Wizard Island of type CINDER_CONE\n" +
                "Successfully imported volcano Mount Tehama of type LAVA_DOME\n" +
                "Successfully imported volcano Mount Scott of type CINDER_CONE\n" +
                "Successfully imported volcano Mono-Inyo Craters of type LAVA_DOME\n" +
                "Successfully imported volcano Black Butte of type CINDER_CONE\n" +
                "Successfully imported volcano Lassen Peak of type LAVA_DOME\n" +
                "Successfully imported volcano Stromboli of type CINDER_CONE\n" +
                "Successfully imported volcano Mount Sinabung of type STRATOVOLCANO\n" +
                "Successfully imported volcano Mount Semeru of type STRATOVOLCANO\n" +
                "Successfully imported volcano Mount Agung of type STRATOVOLCANO\n" +
                "Successfully imported volcano Krakatoa of type CALDERA\n" +
                "Successfully imported volcano Mount Kelud of type STRATOVOLCANO\n" +
                "Successfully imported volcano Mount Bromo of type STRATOVOLCANO\n";

        String[] expectedSplit = expected.split("\\r\\n?|\\n");
        String actual = volcanoService.importVolcanoes();
        String[] actualSplit = actual.split("\\r\\n?|\\n");

        Assertions.assertArrayEquals(expectedSplit, actualSplit);
    }
}
