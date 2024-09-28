package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.service;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<String> findAllBooksAfterYear(int yearForAfter);

    List<String> findAllBooksByAuthorNameOrdered(String firstName, String lastName);
}
