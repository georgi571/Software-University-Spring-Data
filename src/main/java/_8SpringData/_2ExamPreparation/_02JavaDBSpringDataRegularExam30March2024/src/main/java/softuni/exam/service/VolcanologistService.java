package softuni.exam.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

// TODO: Implement all methods
public interface VolcanologistService {

    boolean areImported();

    String readVolcanologistsFromFile() throws IOException;

	String importVolcanologists() throws IOException, JAXBException;

}
