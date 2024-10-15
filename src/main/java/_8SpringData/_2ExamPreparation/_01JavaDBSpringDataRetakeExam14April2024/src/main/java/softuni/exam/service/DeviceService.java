package softuni.exam.service;


import javax.xml.bind.JAXBException;
import java.io.IOException;

// TODO: Implement all methods
public interface DeviceService {

    boolean areImported();

    String readDevicesFromFile() throws IOException;

	String importDevices() throws IOException, JAXBException;

    String exportDevices();

}
