package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.service;



import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._1BookshopSystem.org.softwareuniversityspringdata.data.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAuthorFirstAndLastNameForBooksBeforeYear(int yearForBefore);

    List<String> findAllByOrderByBooksSizeDesc();
}
