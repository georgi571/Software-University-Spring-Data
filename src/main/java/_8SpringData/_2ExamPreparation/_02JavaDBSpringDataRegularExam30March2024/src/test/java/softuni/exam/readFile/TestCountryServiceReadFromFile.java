package softuni.exam.readFile;
//TestCountryServiceReadFromFile

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.service.impl.CountryServiceImpl;

import java.io.IOException;



@ExtendWith(MockitoExtension.class)
public class TestCountryServiceReadFromFile {

    @InjectMocks
    private CountryServiceImpl countryService;

    @Test
    void readCountriesFromFile() throws IOException {

        String expected = "[\n" +
                "  {\n" +
                "    \"name\": \"Italy\",\n" +
                "    \"capital\": \"Rome\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Italy\",\n" +
                "    \"capital\": \"Rome\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Hawaii\",\n" +
                "    \"capital\": \"Honolulu\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Japan\",\n" +
                "    \"capital\": \"Tokyo\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"USA\",\n" +
                "    \"capital\": \"Washington\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Kabardino-Balkarian Republic\",\n" +
                "    \"capital\": \"Nalchik\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Mexico\",\n" +
                "    \"capital\": \"Mexico City\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Iceland\",\n" +
                "    \"capital\": \"Reykjavik\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Russia\",\n" +
                "    \"capital\": \"Moscow\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Tanzania\",\n" +
                "    \"capital\": \"Dodoma\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Ross Island\",\n" +
                "    \"capital\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Indonesia\",\n" +
                "    \"capital\": \"Jakarta\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Guatemala\",\n" +
                "    \"capital\": \"Guatemala City\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Ecuador\",\n" +
                "    \"capital\": \"Quito\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Martinique\",\n" +
                "    \"capital\": \"Fort-de-France\"\n" +
                "  }\n" +
                "]";

        String actual = countryService.readCountriesFromFile()
                .replaceAll("\\r\\n|\\r|\\n", "\n").trim();
        Assertions.assertEquals(expected, actual);
    }
}