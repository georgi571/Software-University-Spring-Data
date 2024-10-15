package softuni.exam.readFile;
//TestDevicesServiceReadFromFile

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.service.impl.DeviceServiceImpl;
import softuni.exam.service.impl.SellerServiceImpl;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class TestDevicesServiceReadFromFile {

    @InjectMocks
    private DeviceServiceImpl deviceService;

    @Test
    void readDevicesFromFile() throws IOException {
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<devices>\n" +
                "    <device>\n" +
                "        <brand>HTC</brand>\n" +
                "        <device_type>SMART_PHONE</device_type>\n" +
                "        <model>Ultra23+</model>\n" +
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
                "    <device>\n" +
                "        <brand>Samsung</brand>\n" +
                "        <device_type>SMART_PHONE</device_type>\n" +
                "        <model>S23</model>\n" +
                "        <price>899.99</price>\n" +
                "        <storage>256</storage>\n" +
                "        <sale_id>2</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>iPhone</brand>\n" +
                "        <device_type>SMART_PHONE</device_type>\n" +
                "        <model>15</model>\n" +
                "        <price>999.00</price>\n" +
                "        <storage>128</storage>\n" +
                "        <sale_id>1</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Samsung</brand>\n" +
                "        <device_type>SMART_PHONE</device_type>\n" +
                "        <model>Galaxy S21</model>\n" +
                "        <price>899.99</price>\n" +
                "        <storage>256</storage>\n" +
                "        <sale_id>2</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Google</brand>\n" +
                "        <device_type>SMART_PHONE</device_type>\n" +
                "        <model>Pixel 6</model>\n" +
                "        <price>799.00</price>\n" +
                "        <storage>128</storage>\n" +
                "        <sale_id>3</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>OnePlus</brand>\n" +
                "        <device_type>SMART_PHONE</device_type>\n" +
                "        <model>9 Pro</model>\n" +
                "        <price>899.00</price>\n" +
                "        <storage>256</storage>\n" +
                "        <sale_id>4</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Apple</brand>\n" +
                "        <device_type>LAPTOP</device_type>\n" +
                "        <model>iPhone 13</model>\n" +
                "        <price>1099.00</price>\n" +
                "        <storage>256</storage>\n" +
                "        <sale_id>5</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Xiaomi</brand>\n" +
                "        <device_type>SMART_PHONE</device_type>\n" +
                "        <model>Redmi Note 10</model>\n" +
                "        <price>299.99</price>\n" +
                "        <storage>64</storage>\n" +
                "        <sale_id>6</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Motorola</brand>\n" +
                "        <device_type>SMART_PHONE</device_type>\n" +
                "        <model>Moto G Power</model>\n" +
                "        <price>249.99</price>\n" +
                "        <storage>128</storage>\n" +
                "        <sale_id>7</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>LG</brand>\n" +
                "        <device_type>SMART_PHONE</device_type>\n" +
                "        <model>Wing</model>\n" +
                "        <price>799.00</price>\n" +
                "        <storage>256</storage>\n" +
                "        <sale_id>8</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Sony</brand>\n" +
                "        <device_type>SMART_PHONE</device_type>\n" +
                "        <model>Xperia 1 III</model>\n" +
                "        <price>1299.00</price>\n" +
                "        <storage>256</storage>\n" +
                "        <sale_id>9</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Asus</brand>\n" +
                "        <device_type>SMART_PHONE</device_type>\n" +
                "        <model>ROG Phone 5</model>\n" +
                "        <price>999.00</price>\n" +
                "        <storage>256</storage>\n" +
                "        <sale_id>10</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Oppo</brand>\n" +
                "        <device_type>SMART_PHONE</device_type>\n" +
                "        <model>Find X3 Pro</model>\n" +
                "        <price>1199.00</price>\n" +
                "        <storage>256</storage>\n" +
                "        <sale_id>11</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Vivo</brand>\n" +
                "        <device_type>SMART_PHONE</device_type>\n" +
                "        <model>X60 Pro</model>\n" +
                "        <price>899.00</price>\n" +
                "        <storage>256</storage>\n" +
                "        <sale_id>12</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Realme</brand>\n" +
                "        <device_type>SMART_PHONE</device_type>\n" +
                "        <model>X7 Pro</model>\n" +
                "        <price>499.00</price>\n" +
                "        <storage>128</storage>\n" +
                "        <sale_id>13</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Nokia</brand>\n" +
                "        <device_type>SMART_PHONE</device_type>\n" +
                "        <model>8.3</model>\n" +
                "        <price>699.00</price>\n" +
                "        <storage>128</storage>\n" +
                "        <sale_id>14</sale_id>\n" +
                "    </device>\n" +
                "      <device>\n" +
                "        <brand>HTC</brand>\n" +
                "        <device_type>SMART_PHONE</device_type>\n" +
                "        <model>U12+</model>\n" +
                "        <price>799.00</price>\n" +
                "        <storage>128</storage>\n" +
                "        <sale_id>20</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Apple</brand>\n" +
                "        <device_type>SMART_WATCH</device_type>\n" +
                "        <model>Watch Series 7</model>\n" +
                "        <price>399.00</price>\n" +
                "        <storage>32</storage>\n" +
                "        <sale_id>1</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Samsung</brand>\n" +
                "        <device_type>SMART_WATCH</device_type>\n" +
                "        <model>Galaxy Watch 4</model>\n" +
                "        <price>349.99</price>\n" +
                "        <storage>16</storage>\n" +
                "        <sale_id>2</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Fitbit</brand>\n" +
                "        <device_type>SMART_WATCH</device_type>\n" +
                "        <model>Versa 3</model>\n" +
                "        <price>229.95</price>\n" +
                "        <storage>4</storage>\n" +
                "        <sale_id>3</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Garmin</brand>\n" +
                "        <device_type>SMART_WATCH</device_type>\n" +
                "        <model>Forerunner 945</model>\n" +
                "        <price>599.99</price>\n" +
                "        <storage>16</storage>\n" +
                "        <sale_id>4</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Xiaomi</brand>\n" +
                "        <device_type>SMART_WATCH</device_type>\n" +
                "        <model>Mi Watch</model>\n" +
                "        <price>99.99</price>\n" +
                "        <storage>8</storage>\n" +
                "        <sale_id>5</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Amazfit</brand>\n" +
                "        <device_type>SMART_WATCH</device_type>\n" +
                "        <model>GTR 3 Pro</model>\n" +
                "        <price>199.99</price>\n" +
                "        <storage>8</storage>\n" +
                "        <sale_id>6</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Fossil</brand>\n" +
                "        <device_type>SMART_WATCH</device_type>\n" +
                "        <model>Gen 6</model>\n" +
                "        <price>299.00</price>\n" +
                "        <storage>8</storage>\n" +
                "        <sale_id>7</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Huawei</brand>\n" +
                "        <device_type>SMART_WATCH</device_type>\n" +
                "        <model>Watch GT 3</model>\n" +
                "        <price>229.99</price>\n" +
                "        <storage>16</storage>\n" +
                "        <sale_id>8</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>OnePlus</brand>\n" +
                "        <device_type>SMART_WATCH</device_type>\n" +
                "        <model>Watch</model>\n" +
                "        <price>159.00</price>\n" +
                "        <storage>4</storage>\n" +
                "        <sale_id>9</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Realme</brand>\n" +
                "        <device_type>SMART_WATCH</device_type>\n" +
                "        <model>Watch 2 Pro</model>\n" +
                "        <price>149.99</price>\n" +
                "        <storage>16</storage>\n" +
                "        <sale_id>10</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Apple</brand>\n" +
                "        <device_type>LAPTOP</device_type>\n" +
                "        <model>MacBook Air</model>\n" +
                "        <price>999.00</price>\n" +
                "        <storage>256</storage>\n" +
                "        <sale_id>1</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Dell</brand>\n" +
                "        <device_type>LAPTOP</device_type>\n" +
                "        <model>XPS 13</model>\n" +
                "        <price>1299.99</price>\n" +
                "        <storage>512</storage>\n" +
                "        <sale_id>2</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Lenovo</brand>\n" +
                "        <device_type>LAPTOP</device_type>\n" +
                "        <model>ThinkPad X1 Carbon</model>\n" +
                "        <price>1499.00</price>\n" +
                "        <storage>512</storage>\n" +
                "        <sale_id>3</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>HP</brand>\n" +
                "        <device_type>LAPTOP</device_type>\n" +
                "        <model>Spectre x360</model>\n" +
                "        <price>1299.00</price>\n" +
                "        <storage>512</storage>\n" +
                "        <sale_id>4</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Acer</brand>\n" +
                "        <device_type>LAPTOP</device_type>\n" +
                "        <model>Swift 3</model>\n" +
                "        <price>699.00</price>\n" +
                "        <storage>256</storage>\n" +
                "        <sale_id>6</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Asus</brand>\n" +
                "        <device_type>LAPTOP</device_type>\n" +
                "        <model>ZenBook 14</model>\n" +
                "        <price>999.00</price>\n" +
                "        <storage>512</storage>\n" +
                "        <sale_id>7</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Razer</brand>\n" +
                "        <device_type>LAPTOP</device_type>\n" +
                "        <model>Blade 15</model>\n" +
                "        <price>1799.00</price>\n" +
                "        <storage>512</storage>\n" +
                "        <sale_id>8</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Alienware</brand>\n" +
                "        <device_type>LAPTOP</device_type>\n" +
                "        <model>m15 R6</model>\n" +
                "        <price>2299.00</price>\n" +
                "        <storage>1000</storage>\n" +
                "        <sale_id>9</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>MSI</brand>\n" +
                "        <device_type>LAPTOP</device_type>\n" +
                "        <model>GS66 Stealth</model>\n" +
                "        <price>1799.00</price>\n" +
                "        <storage>1000</storage>\n" +
                "        <sale_id>10</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Samsung</brand>\n" +
                "        <device_type>LAPTOP</device_type>\n" +
                "        <model>Galaxy Book Pro</model>\n" +
                "        <price>1099.00</price>\n" +
                "        <storage>512</storage>\n" +
                "        <sale_id>11</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>LG</brand>\n" +
                "        <device_type>LAPTOP</device_type>\n" +
                "        <model>Gram 14</model>\n" +
                "        <price>1199.00</price>\n" +
                "        <storage>512</storage>\n" +
                "        <sale_id>12</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Huawei</brand>\n" +
                "        <device_type>LAPTOP</device_type>\n" +
                "        <model>MateBook X Pro</model>\n" +
                "        <price>1499.00</price>\n" +
                "        <storage>512</storage>\n" +
                "        <sale_id>13</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Xiaomi</brand>\n" +
                "        <device_type>LAPTOP</device_type>\n" +
                "        <model>Mi Notebook Pro</model>\n" +
                "        <price>899.00</price>\n" +
                "        <storage>512</storage>\n" +
                "        <sale_id>17</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>MSI</brand>\n" +
                "        <device_type>LAPTOP</device_type>\n" +
                "        <model>Prestige 14</model>\n" +
                "        <price>1399.00</price>\n" +
                "        <storage>512</storage>\n" +
                "        <sale_id>18</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Razer</brand>\n" +
                "        <device_type>LAPTOP</device_type>\n" +
                "        <model>Blade Stealth 13</model>\n" +
                "        <price>1599.00</price>\n" +
                "        <storage>512</storage>\n" +
                "        <sale_id>19</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Lenovo</brand>\n" +
                "        <device_type>LAPTOP</device_type>\n" +
                "        <model>ThinkPad T14</model>\n" +
                "        <price>1399.00</price>\n" +
                "        <storage>512</storage>\n" +
                "        <sale_id>20</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Apple</brand>\n" +
                "        <device_type>TABLET</device_type>\n" +
                "        <model>iPad Air 4</model>\n" +
                "        <price>599.00</price>\n" +
                "        <storage>64</storage>\n" +
                "        <sale_id>1</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Samsung</brand>\n" +
                "        <device_type>TABLET</device_type>\n" +
                "        <model>Galaxy Tab S7+</model>\n" +
                "        <price>849.99</price>\n" +
                "        <storage>128</storage>\n" +
                "        <sale_id>2</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Lenovo</brand>\n" +
                "        <device_type>TABLET</device_type>\n" +
                "        <model>Tab P11 Pro</model>\n" +
                "        <price>499.99</price>\n" +
                "        <storage>128</storage>\n" +
                "        <sale_id>3</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Amazon</brand>\n" +
                "        <device_type>TABLET</device_type>\n" +
                "        <model>Fire HD 10</model>\n" +
                "        <price>149.99</price>\n" +
                "        <storage>32</storage>\n" +
                "        <sale_id>4</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Microsoft</brand>\n" +
                "        <device_type>TABLET</device_type>\n" +
                "        <model>Surface Pro 7</model>\n" +
                "        <price>899.99</price>\n" +
                "        <storage>256</storage>\n" +
                "        <sale_id>5</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Huawei</brand>\n" +
                "        <device_type>TABLET</device_type>\n" +
                "        <model>MatePad Pro</model>\n" +
                "        <price>549.99</price>\n" +
                "        <storage>128</storage>\n" +
                "        <sale_id>6</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Xiaomi</brand>\n" +
                "        <device_type>TABLET</device_type>\n" +
                "        <model>Mi Pad 5</model>\n" +
                "        <price>499.99</price>\n" +
                "        <storage>128</storage>\n" +
                "        <sale_id>7</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Google</brand>\n" +
                "        <device_type>TABLET</device_type>\n" +
                "        <model>Pixel Slate</model>\n" +
                "        <price>799.00</price>\n" +
                "        <storage>128</storage>\n" +
                "        <sale_id>8</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Realme</brand>\n" +
                "        <device_type>TABLET</device_type>\n" +
                "        <model>Pad</model>\n" +
                "        <price>199.00</price>\n" +
                "        <storage>64</storage>\n" +
                "        <sale_id>9</sale_id>\n" +
                "    </device>\n" +
                "    <device>\n" +
                "        <brand>Asus</brand>\n" +
                "        <device_type>TABLET</device_type>\n" +
                "        <model>ZenPad 3S 10</model>\n" +
                "        <price>299.99</price>\n" +
                "        <storage>64</storage>\n" +
                "        <sale_id>10</sale_id>\n" +
                "    </device>\n" +
                "</devices>";


        String actual = deviceService.readDevicesFromFile()
                .replaceAll("\\r\\n|\\r|\\n", "\n").trim();;

        Assertions.assertEquals(expected, actual);
    }
}