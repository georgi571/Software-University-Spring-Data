package softuni.exam.import_Sale;
//TestImportSalesZeroTest000

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import softuni.exam.service.impl.SaleServiceImpl;

import java.io.IOException;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportSalesZeroTest000 {

    @Autowired
    private SaleServiceImpl saleService;

    @Test
    @Sql("/sellers-test-imports.sql")
    void importSalesZeroTest() throws IOException {

        String expected = "Successfully imported sale with number 1000123\n" +
                "Invalid sale\n" +
                "Successfully imported sale with number 1000124\n" +
                "Successfully imported sale with number 3320987\n" +
                "Successfully imported sale with number 8756321\n" +
                "Invalid sale\n" +
                "Successfully imported sale with number 5213445\n" +
                "Successfully imported sale with number 3457893\n" +
                "Successfully imported sale with number 2045678\n" +
                "Successfully imported sale with number 6600123\n" +
                "Successfully imported sale with number 1234567\n" +
                "Successfully imported sale with number 4312897\n" +
                "Successfully imported sale with number 7643289\n" +
                "Successfully imported sale with number 5123901\n" +
                "Successfully imported sale with number 6781234\n" +
                "Successfully imported sale with number 8997654\n" +
                "Successfully imported sale with number 2239876\n" +
                "Successfully imported sale with number 7777777\n" +
                "Successfully imported sale with number 4567890\n" +
                "Successfully imported sale with number 3456789\n" +
                "Successfully imported sale with number 8881234\n" +
                "Successfully imported sale with number 3333333\n" +
                "Successfully imported sale with number 9876543\n" +
                "Successfully imported sale with number 3456780\n";

        String[] expectedSplit = expected.split("\\r\\n?|\\n");
        String actual = saleService.importSales();
        String[] actualSplit = actual.split("\\r\\n?|\\n");

        Assertions.assertArrayEquals(expectedSplit, actualSplit);
    }


}
