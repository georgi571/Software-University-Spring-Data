package softuni.exam.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

// TODO: Implement all methods
public interface SellerService {

    boolean areImported();

    String readSellersFromFile() throws IOException;

    String importSellers() throws IOException;

}
