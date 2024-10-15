package softuni.exam.export;
//TestDevicesServiceExport001

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import softuni.exam.service.impl.DeviceServiceImpl;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestDevicesServiceExportTest001 {

    @Autowired
    private DeviceServiceImpl deviceService;

    @Sql("/export-test-001.sql")
    @Test
    void exportDevices001() {
        String actual = deviceService.exportDevices();

        String expected = "Device brand: Asus\n" +
                "   *Model: ROG Phone 5\n" +
                "   **Storage: 256\n" +
                "   ***Price: 999.00\n" +
                "Device brand: Google\n" +
                "   *Model: Pixel 6\n" +
                "   **Storage: 128\n" +
                "   ***Price: 799.00\n" +
                "Device brand: HTC\n" +
                "   *Model: Ultra23+\n" +
                "   **Storage: 128\n" +
                "   ***Price: 999.00\n" +
                "Device brand: HTC\n" +
                "   *Model: U12+\n" +
                "   **Storage: 128\n" +
                "   ***Price: 799.00\n" +
                "Device brand: iPhone\n" +
                "   *Model: 15\n" +
                "   **Storage: 128\n" +
                "   ***Price: 999.00\n" +
                "Device brand: LG\n" +
                "   *Model: Wing\n" +
                "   **Storage: 256\n" +
                "   ***Price: 799.00\n" +
                "Device brand: Motorola\n" +
                "   *Model: Moto G Power\n" +
                "   **Storage: 128\n" +
                "   ***Price: 249.99\n" +
                "Device brand: Nokia\n" +
                "   *Model: 8.3\n" +
                "   **Storage: 128\n" +
                "   ***Price: 699.00\n" +
                "Device brand: OnePlus\n" +
                "   *Model: 9 Pro\n" +
                "   **Storage: 256\n" +
                "   ***Price: 899.00\n" +
                "Device brand: Realme\n" +
                "   *Model: X7 Pro\n" +
                "   **Storage: 128\n" +
                "   ***Price: 499.00\n" +
                "Device brand: Samsung\n" +
                "   *Model: Galaxy S21\n" +
                "   **Storage: 256\n" +
                "   ***Price: 899.99\n" +
                "Device brand: Vivo\n" +
                "   *Model: X60 Pro\n" +
                "   **Storage: 256\n" +
                "   ***Price: 899.00\n";

        String[] actualSplit = actual.split("\\r\\n?|\\n");
        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        Assertions.assertArrayEquals(expectedSplit,actualSplit);
    }
}
