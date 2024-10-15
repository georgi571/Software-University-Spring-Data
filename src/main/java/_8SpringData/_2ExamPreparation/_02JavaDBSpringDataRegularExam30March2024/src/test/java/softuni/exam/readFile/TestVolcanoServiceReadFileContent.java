package softuni.exam.readFile;
//TestVolcanoServiceReadFileContent

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.service.impl.VolcanoServiceImpl;

import java.io.IOException;



@ExtendWith(MockitoExtension.class)
public class TestVolcanoServiceReadFileContent {

    @InjectMocks
    private VolcanoServiceImpl volcanoService;

    @Test
    void readVolcanoesFileContent() throws IOException {
        String expected = "[\n" +
                "  {\n" +
                "    \"name\": \"Mount Vesuvius\",\n" +
                "    \"elevation\": 1281,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2021-01-01\",\n" +
                "    \"country\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount Vesuvius\",\n" +
                "    \"elevation\": 1281,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2021-01-01\",\n" +
                "    \"country\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mauna Loa\",\n" +
                "    \"elevation\": 4169,\n" +
                "    \"volcanoType\": \"SHIELD_VOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2022-03-15\",\n" +
                "    \"country\": 2\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount Fuji\",\n" +
                "    \"elevation\": 3776,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": false,\n" +
                "    \"lastEruption\": \"1707-12-16\",\n" +
                "    \"country\": 3\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount St. Helens\",\n" +
                "    \"elevation\": 2549,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": false,\n" +
                "    \"lastEruption\": \"1980-05-18\",\n" +
                "    \"country\": 4\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount Kilimanjaro\",\n" +
                "    \"elevation\": 5895,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": null,\n" +
                "    \"country\": 9\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Klyuchevskaya Sopka\",\n" +
                "    \"elevation\": 4754,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2022-02-28\",\n" +
                "    \"country\": 8\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Popocatepetl\",\n" +
                "    \"elevation\": 5393,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2023-04-05\",\n" +
                "    \"country\": 6\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount Elbrus\",\n" +
                "    \"elevation\": 5642,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": false,\n" +
                "    \"lastEruption\": null,\n" +
                "    \"country\": 5\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Eyjafjallajokull\",\n" +
                "    \"elevation\": 1666,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2010-06-01\",\n" +
                "    \"country\": 7\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount Erebus\",\n" +
                "    \"elevation\": 3794,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": null,\n" +
                "    \"country\": 10\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount Rainier\",\n" +
                "    \"elevation\": 4392,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": null,\n" +
                "    \"country\": 4\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Piton de la Fournaise\",\n" +
                "    \"elevation\": 2632,\n" +
                "    \"volcanoType\": \"SHIELD_VOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2022-10-05\",\n" +
                "    \"country\": 12\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount Etna\",\n" +
                "    \"elevation\": 3357,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2023-11-11\",\n" +
                "    \"country\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount Shasta\",\n" +
                "    \"elevation\": 4322,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": false,\n" +
                "    \"lastEruption\": null,\n" +
                "    \"country\": 4\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount Ijen\",\n" +
                "    \"elevation\": 2799,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2019-01-01\",\n" +
                "    \"country\": 11\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount Rinjani\",\n" +
                "    \"elevation\": 3726,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2016-11-01\",\n" +
                "    \"country\": 11\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount Merapi\",\n" +
                "    \"elevation\": 2884,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2023-12-03\",\n" +
                "    \"country\": 11\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount Tambora\",\n" +
                "    \"elevation\": 2850,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"1967-01-10\",\n" +
                "    \"country\": 11\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount Pelee\",\n" +
                "    \"elevation\": 1397,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"1932-01-01\",\n" +
                "    \"country\": 14\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Pacaya\",\n" +
                "    \"elevation\": 2552,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2023-01-08\",\n" +
                "    \"country\": 12\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Novarupta\",\n" +
                "    \"elevation\": 841,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"1912-10-02\",\n" +
                "    \"country\": 4\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Cotopaxi\",\n" +
                "    \"elevation\": 5897,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2022-04-20\",\n" +
                "    \"country\": 13\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount Cleveland\",\n" +
                "    \"elevation\": 1730,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2017-05-17\",\n" +
                "    \"country\": 4\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Santa Maria\",\n" +
                "    \"elevation\": 3772,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2021-08-14\",\n" +
                "    \"country\": 12\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount Aniakchak\",\n" +
                "    \"elevation\": 1290,\n" +
                "    \"volcanoType\": \"CALDERA\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"1931-06-06\",\n" +
                "    \"country\": 4\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Paricutin\",\n" +
                "    \"elevation\": 2800,\n" +
                "    \"volcanoType\": \"CINDER_CONE\",\n" +
                "    \"isActive\": false,\n" +
                "    \"lastEruption\": \"2021-02-14\",\n" +
                "    \"country\": 6\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount Jefferson\",\n" +
                "    \"elevation\": 3199,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2022-05-19\",\n" +
                "    \"country\": 4\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Wizard Island\",\n" +
                "    \"elevation\": 2300,\n" +
                "    \"volcanoType\": \"CINDER_CONE\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2023-02-10\",\n" +
                "    \"country\": 4\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount Tehama\",\n" +
                "    \"elevation\": 3367,\n" +
                "    \"volcanoType\": \"LAVA_DOME\",\n" +
                "    \"isActive\": false,\n" +
                "    \"lastEruption\": null,\n" +
                "    \"country\": 4\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount Scott\",\n" +
                "    \"elevation\": 2758,\n" +
                "    \"volcanoType\": \"CINDER_CONE\",\n" +
                "    \"isActive\": false,\n" +
                "    \"lastEruption\": null,\n" +
                "    \"country\": 4\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mono-Inyo Craters\",\n" +
                "    \"elevation\": 2692,\n" +
                "    \"volcanoType\": \"LAVA_DOME\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"1722-11-05\",\n" +
                "    \"country\": 4\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Black Butte\",\n" +
                "    \"elevation\": 1945,\n" +
                "    \"volcanoType\": \"CINDER_CONE\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": null,\n" +
                "    \"country\": 4\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Lassen Peak\",\n" +
                "    \"elevation\": 3187,\n" +
                "    \"volcanoType\": \"LAVA_DOME\",\n" +
                "    \"isActive\": false,\n" +
                "    \"lastEruption\": \"1921-05-05\",\n" +
                "    \"country\": 4\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Stromboli\",\n" +
                "    \"elevation\": 924,\n" +
                "    \"volcanoType\": \"CINDER_CONE\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2014-08-03\",\n" +
                "    \"country\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount Sinabung\",\n" +
                "    \"elevation\": 2460,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2021-02-07\",\n" +
                "    \"country\": 11\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount Semeru\",\n" +
                "    \"elevation\": 3676,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2021-01-16\",\n" +
                "    \"country\": 11\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount Agung\",\n" +
                "    \"elevation\": 3031,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2019-11-26\",\n" +
                "    \"country\": 11\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Krakatoa\",\n" +
                "    \"elevation\": 813,\n" +
                "    \"volcanoType\": \"CALDERA\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2022-02-03\",\n" +
                "    \"country\": 11\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount Kelud\",\n" +
                "    \"elevation\": 1731,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2014-02-14\",\n" +
                "    \"country\": 11\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mount Bromo\",\n" +
                "    \"elevation\": 2329,\n" +
                "    \"volcanoType\": \"STRATOVOLCANO\",\n" +
                "    \"isActive\": true,\n" +
                "    \"lastEruption\": \"2016-03-17\",\n" +
                "    \"country\": 11\n" +
                "  }\n" +
                "]";

        String actual = volcanoService.readVolcanoesFileContent()
                .replaceAll("\\r\\n|\\r|\\n", "\n").trim();

        Assertions.assertEquals(expected, actual);
    }
}