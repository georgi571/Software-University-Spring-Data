package softuni.exam.import_Sellers;
//TestImportSellersZeroTest000

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import softuni.exam.service.impl.SellerServiceImpl;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportSellersZeroTest000 {
    @Autowired
    private SellerServiceImpl sellerService;



    @Test
    void importSellersZeroTest() throws IOException {
        String actual = sellerService.importSellers();

        String expected = "Successfully imported seller John Harrison\n" +
                "Successfully imported seller Alice Smith\n" +
                "Invalid seller\n" +
                "Successfully imported seller Michael Johnson\n" +
                "Successfully imported seller Emily Williams\n" +
                "Successfully imported seller Daniel Brown\n" +
                "Successfully imported seller Sophia Davis\n" +
                "Successfully imported seller Matthew Miller\n" +
                "Successfully imported seller Olivia Wilson\n" +
                "Successfully imported seller James Taylor\n" +
                "Successfully imported seller Emma Anderson\n" +
                "Successfully imported seller David Thomas\n" +
                "Successfully imported seller Isabella Jackson\n" +
                "Successfully imported seller William White\n" +
                "Successfully imported seller Ava Harris\n" +
                "Successfully imported seller Joseph Martin\n" +
                "Successfully imported seller Mia Thompson\n" +
                "Successfully imported seller Charles Garcia\n" +
                "Successfully imported seller Evelyn Martinez\n" +
                "Successfully imported seller Andrew Robinson\n" +
                "Successfully imported seller Charlotte Clark\n";


        String[] actualSplit = actual.split("\\r\\n?|\\n");
        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        Assertions.assertArrayEquals(expectedSplit,actualSplit);
    }



}
