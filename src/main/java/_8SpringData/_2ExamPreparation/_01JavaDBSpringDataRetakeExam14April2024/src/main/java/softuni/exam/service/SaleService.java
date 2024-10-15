package softuni.exam.service;

import java.io.IOException;

// TODO: Implement all methods
public interface SaleService {

    boolean areImported();

    String readSalesFileContent() throws IOException;

    String importSales() throws IOException;
}
