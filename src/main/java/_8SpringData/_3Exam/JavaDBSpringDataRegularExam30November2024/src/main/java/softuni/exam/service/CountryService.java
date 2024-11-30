package softuni.exam.service;

import java.io.IOException;

public interface CountryService {

    boolean areImported();

    String readCountryFileContent() throws IOException;

    String importCountries() throws IOException;
}
