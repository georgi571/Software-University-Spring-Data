package softuni.exam.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface PictureService {
    String importPictures() throws IOException;

    boolean areImported();

    String readPicturesXmlFile() throws JAXBException;
}
