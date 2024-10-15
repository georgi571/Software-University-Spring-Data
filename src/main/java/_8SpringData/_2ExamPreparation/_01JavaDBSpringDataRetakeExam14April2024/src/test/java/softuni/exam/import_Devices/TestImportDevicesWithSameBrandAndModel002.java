package softuni.exam.import_Devices;
//TestImportDeviceWithSameBrandAndModel002

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import softuni.exam.service.impl.DeviceServiceImpl;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportDevicesWithSameBrandAndModel002 {

    @Autowired
    private DeviceServiceImpl deviceService;

    @Sql({"/sellers-test-imports.sql", "/sales-test-imports.sql"})
    @Test
    void importDeviceWithSameBrandAndModel002() throws IOException, JAXBException, NoSuchFieldException, IllegalAccessException {

        rewriteFileForTest();
        String actual = deviceService.importDevices();
        String[] actualSplit = actual.split("\\r\\n?|\\n");

        String expected = "Successfully imported device of type SMART_PHONE with brand Samsung\n" +
                "Invalid device";
        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        returnOriginalValue();
        Assertions.assertArrayEquals(expectedSplit, actualSplit);
    }

    private void rewriteFileForTest() {
        File originalJsonFile = getOriginalFile();

        String testXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<devices>\n" +
                "    <device>\n" +
                "        <brand>Samsung</brand>\n" +
                "        <device_type>SMART_PHONE</device_type>\n" +
                "        <model>S23</model>\n" +
                "        <price>999.00</price>\n" +
                "        <storage>128</storage>\n" +
                "        <sale_id>1</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Samsung</brand>\n" +
                "        <device_type>SMART_PHONE</device_type>\n" +
                "        <model>S23</model>\n" +
                "        <price>1899.00</price>\n" +
                "        <storage>256</storage>\n" +
                "        <sale_id>2</sale_id>\n" +
                "    </device>\n" +
                "</devices>\n";

        try {
            FileWriter f2 = new FileWriter(originalJsonFile, false);
            f2.write(testXML);
            f2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getOriginalFile() {
        return new File("src/main/resources/files/xml/devices.xml");
    }

    private void returnOriginalValue() {

        try {
            FileWriter f2 = new FileWriter(getOriginalFile(), false);
            String testOriginalFile = Files.readString(Path.of("src/test/resources/original-files/devices.xml"));
            f2.write(testOriginalFile);
            f2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
