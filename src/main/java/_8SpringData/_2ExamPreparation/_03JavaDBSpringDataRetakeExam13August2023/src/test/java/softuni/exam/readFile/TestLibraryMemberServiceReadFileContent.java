package softuni.exam.readFile;
//TestLibraryMemberServiceReadFileContent

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.service.impl.LibraryMemberServiceImpl;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class TestLibraryMemberServiceReadFileContent {

    @InjectMocks
    private LibraryMemberServiceImpl libraryMemberService;

     @Test
    void readStarsFileContent() throws IOException {
        String expected = "[\n" +
                "  {\n" +
                "    \"address\": \"123 Main St\",\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Doe\",\n" +
                "    \"phoneNumber\": \"555-1111\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"13 Main St\",\n" +
                "    \"firstName\": \"Joah\",\n" +
                "    \"lastName\": \"Doevill\",\n" +
                "    \"phoneNumber\": \"555-1111\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"23 Einsteinufer str\",\n" +
                "    \"firstName\": null,\n" +
                "    \"lastName\": \"Schmidt\",\n" +
                "    \"phoneNumber\": \"525-22\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"456 Elm St\",\n" +
                "    \"firstName\": \"Jane\",\n" +
                "    \"lastName\": \"Smith\",\n" +
                "    \"phoneNumber\": \"555-2222\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": null,\n" +
                "    \"firstName\": \"Michael\",\n" +
                "    \"lastName\": \"Johnson\",\n" +
                "    \"phoneNumber\": \"555-3333\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"321 Maple Rd\",\n" +
                "    \"firstName\": \"Emily\",\n" +
                "    \"lastName\": \"Williams\",\n" +
                "    \"phoneNumber\": \"5555-4444-2213-594810\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"555 Pine Blvd\",\n" +
                "    \"firstName\": \"David\",\n" +
                "    \"lastName\": \"Brown\",\n" +
                "    \"phoneNumber\": \"555-5555\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"777 Cedar Ln\",\n" +
                "    \"firstName\": \"Sarah\",\n" +
                "    \"lastName\": \"Jones\",\n" +
                "    \"phoneNumber\": \"555-6666\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"888 Birch Dr\",\n" +
                "    \"firstName\": \"Matthew\",\n" +
                "    \"lastName\": \"Miller\",\n" +
                "    \"phoneNumber\": \"555-7777\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"999 Walnut St\",\n" +
                "    \"firstName\": \"Jennifer\",\n" +
                "    \"lastName\": \"Taylor\",\n" +
                "    \"phoneNumber\": \"555-8888\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"111 Willow Ave\",\n" +
                "    \"firstName\": \"Daniel\",\n" +
                "    \"lastName\": \"Anderson\",\n" +
                "    \"phoneNumber\": \"555-9999\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"222 Redwood Rd\",\n" +
                "    \"firstName\": \"Jessica\",\n" +
                "    \"lastName\": \"Thomas\",\n" +
                "    \"phoneNumber\": \"555-1010\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"333 Spruce Ct\",\n" +
                "    \"firstName\": \"Christopher\",\n" +
                "    \"lastName\": \"Lee\",\n" +
                "    \"phoneNumber\": \"555-1112\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"444 Birch Ln\",\n" +
                "    \"firstName\": \"Laura\",\n" +
                "    \"lastName\": \"Garcia\",\n" +
                "    \"phoneNumber\": \"555-1213\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"555 Oak Blvd\",\n" +
                "    \"firstName\": \"James\",\n" +
                "    \"lastName\": \"Martinez\",\n" +
                "    \"phoneNumber\": \"555-1314\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"666 Elm Rd\",\n" +
                "    \"firstName\": \"Amanda\",\n" +
                "    \"lastName\": \"Robinson\",\n" +
                "    \"phoneNumber\": \"555-1415\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"777 Cedar Ave\",\n" +
                "    \"firstName\": \"Robert\",\n" +
                "    \"lastName\": \"Clark\",\n" +
                "    \"phoneNumber\": \"555-1516\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"888 Pine Ct\",\n" +
                "    \"firstName\": \"Melissa\",\n" +
                "    \"lastName\": \"Lewis\",\n" +
                "    \"phoneNumber\": \"555-1617\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"999 Maple Ln\",\n" +
                "    \"firstName\": \"William\",\n" +
                "    \"lastName\": \"Hill\",\n" +
                "    \"phoneNumber\": \"555-1718\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"111 Birch Dr\",\n" +
                "    \"firstName\": \"Michelle\",\n" +
                "    \"lastName\": \"Lopez\",\n" +
                "    \"phoneNumber\": \"555-1819\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"222 Elm St\",\n" +
                "    \"firstName\": \"Elizabeth\",\n" +
                "    \"lastName\": \"Green\",\n" +
                "    \"phoneNumber\": \"555-1920\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"333 Oak Ave\",\n" +
                "    \"firstName\": \"Andrew\",\n" +
                "    \"lastName\": \"Adams\",\n" +
                "    \"phoneNumber\": \"555-2021\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"Hauptstraße 1\",\n" +
                "    \"firstName\": \"Oliver\",\n" +
                "    \"lastName\": \"Schmidt\",\n" +
                "    \"phoneNumber\": \"555-3030\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"Bahnhofstraße 15\",\n" +
                "    \"firstName\": \"Sophie\",\n" +
                "    \"lastName\": \"Müller\",\n" +
                "    \"phoneNumber\": \"555-4040\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"Kirchplatz 8\",\n" +
                "    \"firstName\": \"Lukas\",\n" +
                "    \"lastName\": \"Fischer\",\n" +
                "    \"phoneNumber\": \"555-5050\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"Dorfstraße 25\",\n" +
                "    \"firstName\": \"Hannah\",\n" +
                "    \"lastName\": \"Weber\",\n" +
                "    \"phoneNumber\": \"555-6060\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"Schulweg 10\",\n" +
                "    \"firstName\": \"Finn\",\n" +
                "    \"lastName\": \"Koch\",\n" +
                "    \"phoneNumber\": \"555-7070\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"Bergstraße 5\",\n" +
                "    \"firstName\": \"Emma\",\n" +
                "    \"lastName\": \"Lehmann\",\n" +
                "    \"phoneNumber\": \"555-8080\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"Mühlenweg 3\",\n" +
                "    \"firstName\": \"Noah\",\n" +
                "    \"lastName\": \"Huber\",\n" +
                "    \"phoneNumber\": \"555-9090\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"Rosenweg 12\",\n" +
                "    \"firstName\": \"Mia\",\n" +
                "    \"lastName\": \"Schneider\",\n" +
                "    \"phoneNumber\": \"555-0101\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"Wiesenstraße 18\",\n" +
                "    \"firstName\": \"Elias\",\n" +
                "    \"lastName\": \"Wolf\",\n" +
                "    \"phoneNumber\": \"555-1212\"\n" +
                "  }\n" +
                "]";

        String actual = libraryMemberService.readLibraryMembersFileContent();

        Assertions.assertEquals(expected, actual);
    }
}