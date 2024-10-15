package softuni.exam.readFile;
//TestSaleServiceReadFileContent

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.service.impl.SaleServiceImpl;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class TestSalesServiceReadFileContent {

    @InjectMocks
    private SaleServiceImpl saleService;

    @Test
    void readSalesFileContent() throws IOException {
        String expected = "[\n" +
                "  {\n" +
                "    \"discounted\": true,\n" +
                "    \"number\": \"1000123\",\n" +
                "    \"saleDate\": \"2022-02-02 12:43:00\",\n" +
                "    \"seller\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": true,\n" +
                "    \"number\": \"1000123\",\n" +
                "    \"saleDate\": \"2022-02-02 12:43:00\",\n" +
                "    \"seller\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": true,\n" +
                "    \"number\": \"1000124\",\n" +
                "    \"saleDate\": \"2023-12-02 12:10:00\",\n" +
                "    \"seller\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": true,\n" +
                "    \"number\": \"3320987\",\n" +
                "    \"saleDate\": \"2022-09-15 08:23:00\",\n" +
                "    \"seller\": 12\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": false,\n" +
                "    \"number\": \"8756321\",\n" +
                "    \"saleDate\": \"2021-11-30 17:50:00\",\n" +
                "    \"seller\": 5\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": false,\n" +
                "    \"number\": \"8756\",\n" +
                "    \"saleDate\": \"2021-11-30 17:50:00\",\n" +
                "    \"seller\": 5\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": true,\n" +
                "    \"number\": \"5213445\",\n" +
                "    \"saleDate\": \"2023-05-22 10:15:00\",\n" +
                "    \"seller\": 18\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": false,\n" +
                "    \"number\": \"3457893\",\n" +
                "    \"saleDate\": \"2020-08-04 14:32:00\",\n" +
                "    \"seller\": 10\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": true,\n" +
                "    \"number\": \"2045678\",\n" +
                "    \"saleDate\": \"2023-07-11 19:27:00\",\n" +
                "    \"seller\": 2\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": false,\n" +
                "    \"number\": \"6600123\",\n" +
                "    \"saleDate\": \"2021-04-28 11:55:00\",\n" +
                "    \"seller\": 8\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": true,\n" +
                "    \"number\": \"1234567\",\n" +
                "    \"saleDate\": \"2020-10-03 16:48:00\",\n" +
                "    \"seller\": 16\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": false,\n" +
                "    \"number\": \"4312897\",\n" +
                "    \"saleDate\": \"2022-03-17 13:20:00\",\n" +
                "    \"seller\": 13\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": true,\n" +
                "    \"number\": \"7643289\",\n" +
                "    \"saleDate\": \"2024-01-01 12:00:00\",\n" +
                "    \"seller\": 3\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": false,\n" +
                "    \"number\": \"5123901\",\n" +
                "    \"saleDate\": \"2020-12-09 09:41:00\",\n" +
                "    \"seller\": 7\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": true,\n" +
                "    \"number\": \"6781234\",\n" +
                "    \"saleDate\": \"2022-06-05 20:13:00\",\n" +
                "    \"seller\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": false,\n" +
                "    \"number\": \"8997654\",\n" +
                "    \"saleDate\": \"2023-08-18 18:02:00\",\n" +
                "    \"seller\": 19\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": true,\n" +
                "    \"number\": \"2239876\",\n" +
                "    \"saleDate\": \"2021-02-14 15:28:00\",\n" +
                "    \"seller\": 11\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": false,\n" +
                "    \"number\": \"7777777\",\n" +
                "    \"saleDate\": \"2020-07-27 07:39:00\",\n" +
                "    \"seller\": 17\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": true,\n" +
                "    \"number\": \"4567890\",\n" +
                "    \"saleDate\": \"2023-01-29 09:05:00\",\n" +
                "    \"seller\": 4\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": false,\n" +
                "    \"number\": \"3456789\",\n" +
                "    \"saleDate\": \"2022-11-10 12:24:00\",\n" +
                "    \"seller\": 6\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": true,\n" +
                "    \"number\": \"8881234\",\n" +
                "    \"saleDate\": \"2020-05-01 22:45:00\",\n" +
                "    \"seller\": 15\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": false,\n" +
                "    \"number\": \"3333333\",\n" +
                "    \"saleDate\": \"2022-04-07 07:17:00\",\n" +
                "    \"seller\": 14\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": true,\n" +
                "    \"number\": \"9876543\",\n" +
                "    \"saleDate\": \"2021-09-23 10:50:00\",\n" +
                "    \"seller\": 9\n" +
                "  },\n" +
                "  {\n" +
                "    \"discounted\": false,\n" +
                "    \"number\": \"3456780\",\n" +
                "    \"saleDate\": \"2020-02-18 14:08:00\",\n" +
                "    \"seller\": 12\n" +
                "  }\n" +
                "]";

        String actual = saleService.readSalesFileContent()
                .replaceAll("\\r\\n|\\r|\\n", "\n").trim();

        Assertions.assertEquals(expected, actual);
    }
}