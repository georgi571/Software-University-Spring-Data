package _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.service;


import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.model.entity.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();

    List<String> getAllAuthorsWhichFirstNameEndWithGivenLetters(String letters); // 6th exercise

    int getTotalBookCopiesByGivenAuthorFirstAndLastName(String firstName, String lastName); // 10th exercise

}
