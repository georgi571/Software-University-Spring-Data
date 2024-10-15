package softuni.exam.readFile;
//TestSellerServiceReadFromFile

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.service.impl.SellerServiceImpl;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class TestSellerServiceReadFromFile {

    @InjectMocks
    private SellerServiceImpl sellerService;

    @Test
    void readSellersFromFile() throws IOException {

        String expected = "[\n" +
                "  {\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Harrison\",\n" +
                "    \"personalNumber\": \"123123\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Alice\",\n" +
                "    \"lastName\": \"Smith\",\n" +
                "    \"personalNumber\": \"456456\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Smith\",\n" +
                "    \"personalNumber\": \"156456\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Michael\",\n" +
                "    \"lastName\": \"Johnson\",\n" +
                "    \"personalNumber\": \"789789\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Emily\",\n" +
                "    \"lastName\": \"Williams\",\n" +
                "    \"personalNumber\": \"101010\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Daniel\",\n" +
                "    \"lastName\": \"Brown\",\n" +
                "    \"personalNumber\": \"111222\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Sophia\",\n" +
                "    \"lastName\": \"Davis\",\n" +
                "    \"personalNumber\": \"333444\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Matthew\",\n" +
                "    \"lastName\": \"Miller\",\n" +
                "    \"personalNumber\": \"555666\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Olivia\",\n" +
                "    \"lastName\": \"Wilson\",\n" +
                "    \"personalNumber\": \"777888\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"James\",\n" +
                "    \"lastName\": \"Taylor\",\n" +
                "    \"personalNumber\": \"999000\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Emma\",\n" +
                "    \"lastName\": \"Anderson\",\n" +
                "    \"personalNumber\": \"121212\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"David\",\n" +
                "    \"lastName\": \"Thomas\",\n" +
                "    \"personalNumber\": \"131313\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Isabella\",\n" +
                "    \"lastName\": \"Jackson\",\n" +
                "    \"personalNumber\": \"141414\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"William\",\n" +
                "    \"lastName\": \"White\",\n" +
                "    \"personalNumber\": \"151515\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Ava\",\n" +
                "    \"lastName\": \"Harris\",\n" +
                "    \"personalNumber\": \"161616\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Joseph\",\n" +
                "    \"lastName\": \"Martin\",\n" +
                "    \"personalNumber\": \"171717\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Mia\",\n" +
                "    \"lastName\": \"Thompson\",\n" +
                "    \"personalNumber\": \"181818\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Charles\",\n" +
                "    \"lastName\": \"Garcia\",\n" +
                "    \"personalNumber\": \"191919\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Evelyn\",\n" +
                "    \"lastName\": \"Martinez\",\n" +
                "    \"personalNumber\": \"202020\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Andrew\",\n" +
                "    \"lastName\": \"Robinson\",\n" +
                "    \"personalNumber\": \"212121\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Charlotte\",\n" +
                "    \"lastName\": \"Clark\",\n" +
                "    \"personalNumber\": \"222222\"\n" +
                "  }\n" +
                "]";

        String actual = sellerService.readSellersFromFile()
                .replaceAll("\\r\\n|\\r|\\n", "\n").trim();
        Assertions.assertEquals(expected, actual);
    }
}