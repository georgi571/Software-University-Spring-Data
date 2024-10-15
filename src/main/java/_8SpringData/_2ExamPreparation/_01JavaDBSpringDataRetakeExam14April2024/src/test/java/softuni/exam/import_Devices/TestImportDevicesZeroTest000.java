package softuni.exam.import_Devices;
//TestImportDevicesZeroTest000

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import softuni.exam.service.impl.DeviceServiceImpl;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportDevicesZeroTest000 {

    @Autowired
    private DeviceServiceImpl deviceService;

    @Sql({"/sellers-test-imports.sql", "/sales-test-imports.sql"})
    @Test
    void importDevicesZeroTest() throws JAXBException, IOException {
        String expected = "Successfully imported device of type SMART_PHONE with brand HTC\n" +
                "Successfully imported device of type SMART_PHONE with brand Samsung\n" +
                "Invalid device\n" +
                "Successfully imported device of type SMART_PHONE with brand iPhone\n" +
                "Successfully imported device of type SMART_PHONE with brand Samsung\n" +
                "Successfully imported device of type SMART_PHONE with brand Google\n" +
                "Successfully imported device of type SMART_PHONE with brand OnePlus\n" +
                "Successfully imported device of type LAPTOP with brand Apple\n" +
                "Successfully imported device of type SMART_PHONE with brand Xiaomi\n" +
                "Successfully imported device of type SMART_PHONE with brand Motorola\n" +
                "Successfully imported device of type SMART_PHONE with brand LG\n" +
                "Successfully imported device of type SMART_PHONE with brand Sony\n" +
                "Successfully imported device of type SMART_PHONE with brand Asus\n" +
                "Successfully imported device of type SMART_PHONE with brand Oppo\n" +
                "Successfully imported device of type SMART_PHONE with brand Vivo\n" +
                "Successfully imported device of type SMART_PHONE with brand Realme\n" +
                "Successfully imported device of type SMART_PHONE with brand Nokia\n" +
                "Successfully imported device of type SMART_PHONE with brand HTC\n" +
                "Successfully imported device of type SMART_WATCH with brand Apple\n" +
                "Successfully imported device of type SMART_WATCH with brand Samsung\n" +
                "Successfully imported device of type SMART_WATCH with brand Fitbit\n" +
                "Successfully imported device of type SMART_WATCH with brand Garmin\n" +
                "Successfully imported device of type SMART_WATCH with brand Xiaomi\n" +
                "Successfully imported device of type SMART_WATCH with brand Amazfit\n" +
                "Successfully imported device of type SMART_WATCH with brand Fossil\n" +
                "Successfully imported device of type SMART_WATCH with brand Huawei\n" +
                "Successfully imported device of type SMART_WATCH with brand OnePlus\n" +
                "Successfully imported device of type SMART_WATCH with brand Realme\n" +
                "Successfully imported device of type LAPTOP with brand Apple\n" +
                "Successfully imported device of type LAPTOP with brand Dell\n" +
                "Successfully imported device of type LAPTOP with brand Lenovo\n" +
                "Successfully imported device of type LAPTOP with brand HP\n" +
                "Successfully imported device of type LAPTOP with brand Acer\n" +
                "Successfully imported device of type LAPTOP with brand Asus\n" +
                "Successfully imported device of type LAPTOP with brand Razer\n" +
                "Successfully imported device of type LAPTOP with brand Alienware\n" +
                "Successfully imported device of type LAPTOP with brand MSI\n" +
                "Successfully imported device of type LAPTOP with brand Samsung\n" +
                "Successfully imported device of type LAPTOP with brand LG\n" +
                "Successfully imported device of type LAPTOP with brand Huawei\n" +
                "Successfully imported device of type LAPTOP with brand Xiaomi\n" +
                "Successfully imported device of type LAPTOP with brand MSI\n" +
                "Successfully imported device of type LAPTOP with brand Razer\n" +
                "Successfully imported device of type LAPTOP with brand Lenovo\n" +
                "Successfully imported device of type TABLET with brand Apple\n" +
                "Successfully imported device of type TABLET with brand Samsung\n" +
                "Successfully imported device of type TABLET with brand Lenovo\n" +
                "Successfully imported device of type TABLET with brand Amazon\n" +
                "Successfully imported device of type TABLET with brand Microsoft\n" +
                "Successfully imported device of type TABLET with brand Huawei\n" +
                "Successfully imported device of type TABLET with brand Xiaomi\n" +
                "Successfully imported device of type TABLET with brand Google\n" +
                "Successfully imported device of type TABLET with brand Realme\n" +
                "Successfully imported device of type TABLET with brand Asus\n";

        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        String actual = deviceService.importDevices();
        String[] actualSplit = actual.split("\\r\\n?|\\n");



        Assertions.assertArrayEquals(expectedSplit, actualSplit);
    }
}

