package softuni.exam.export;
//TestVolcanoServiceExport001DEL

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import softuni.exam.service.impl.VolcanoServiceImpl;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestVolcanoServiceExport001 {

    @Autowired
    private VolcanoServiceImpl volcanoService;

    @Sql("/export-test-001.sql")
    @Test
    void exportVolcano001() {
        String actual = volcanoService.exportVolcanoes();

        String expected = "Volcano: Cotopaxi\n" +
                "   *Located in: Ecuador\n" +
                "   **Elevation: 5897\n" +
                "   ***Last eruption on: 2022-04-20\n" +
                "Volcano: Popocatepetl\n" +
                "   *Located in: Mexico\n" +
                "   **Elevation: 5393\n" +
                "   ***Last eruption on: 2023-04-05\n" +
                "Volcano: Klyuchevskaya Sopka\n" +
                "   *Located in: Russia\n" +
                "   **Elevation: 4754\n" +
                "   ***Last eruption on: 2022-02-28\n" +
                "Volcano: Mauna Loa\n" +
                "   *Located in: Hawaii\n" +
                "   **Elevation: 4169\n" +
                "   ***Last eruption on: 2022-03-15\n" +
                "Volcano: Santa Maria\n" +
                "   *Located in: Guatemala\n" +
                "   **Elevation: 3772\n" +
                "   ***Last eruption on: 2021-08-14\n" +
                "Volcano: Mount Rinjani\n" +
                "   *Located in: Indonesia\n" +
                "   **Elevation: 3726\n" +
                "   ***Last eruption on: 2016-11-01\n" +
                "Volcano: Mount Semeru\n" +
                "   *Located in: Indonesia\n" +
                "   **Elevation: 3676\n" +
                "   ***Last eruption on: 2021-01-16\n" +
                "Volcano: Mount Etna\n" +
                "   *Located in: Italy\n" +
                "   **Elevation: 3357\n" +
                "   ***Last eruption on: 2023-11-11\n" +
                "Volcano: Mount Jefferson\n" +
                "   *Located in: USA\n" +
                "   **Elevation: 3199\n" +
                "   ***Last eruption on: 2022-05-19\n" +
                "Volcano: Mount Agung\n" +
                "   *Located in: Indonesia\n" +
                "   **Elevation: 3031\n" +
                "   ***Last eruption on: 2019-11-26";

        String[] actualSplit = actual.split("\\r\\n?|\\n");
        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        Assertions.assertArrayEquals(expectedSplit,actualSplit);
    }
}
