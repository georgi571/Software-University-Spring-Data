package softuni.exam.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

// TODO: Implement all methods
public interface BorrowingRecordsService {

    boolean areImported();

    String readBorrowingRecordsFromFile() throws IOException;

	String importBorrowingRecords() throws IOException, JAXBException;

    String exportBorrowingRecords();
}
