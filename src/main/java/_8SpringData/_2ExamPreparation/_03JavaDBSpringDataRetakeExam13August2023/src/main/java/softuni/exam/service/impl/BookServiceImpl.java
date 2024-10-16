package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.BookSeedDTO;
import softuni.exam.models.entity.Book;
import softuni.exam.repository.BookRepository;
import softuni.exam.service.BookService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final String PATH_INPUT = "src/main/resources/files/json/books.json";

    private final BookRepository bookRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public BookServiceImpl(BookRepository bookRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.bookRepository = bookRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.bookRepository.count() > 0;
    }

    @Override
    public String readBooksFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importBooks() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        BookSeedDTO[] bookSeedDTOS = this.gson.fromJson(new FileReader(PATH_INPUT), BookSeedDTO[].class);
        for (BookSeedDTO bookSeedDTO : bookSeedDTOS) {
            Optional<Book> bookOptional = this.bookRepository.findByTitle(bookSeedDTO.getTitle());
            if (!this.validationUtil.isValid(bookSeedDTO) || bookOptional.isPresent()) {
                stringBuilder.append("Invalid book").append(System.lineSeparator());
                continue;
            }
            Book book = this.modelMapper.map(bookSeedDTO, Book.class);
            this.bookRepository.saveAndFlush(book);
            stringBuilder.append(String.format("Successfully imported book %s - %s", book.getAuthor(), book.getTitle())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
