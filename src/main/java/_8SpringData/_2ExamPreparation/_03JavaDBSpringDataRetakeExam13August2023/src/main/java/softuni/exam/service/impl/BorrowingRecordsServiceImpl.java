package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.BookSeedDTO;
import softuni.exam.models.dto.xml.BorrowingRecordSeedDTO;
import softuni.exam.models.dto.xml.BorrowingRecordSeedRootDTO;
import softuni.exam.models.dto.xml.LibraryMemberSeedDTO;
import softuni.exam.models.entity.Book;
import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.models.entity.Genre;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.BookRepository;
import softuni.exam.repository.BorrowingRecordRepository;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.BorrowingRecordsService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BorrowingRecordsServiceImpl implements BorrowingRecordsService {

    private final String PATH_INPUT = "src/main/resources/files/xml/borrowing-records.xml";

    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookRepository bookRepository;
    private final LibraryMemberRepository libraryMemberRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;


    public BorrowingRecordsServiceImpl(BorrowingRecordRepository borrowingRecordRepository, BookRepository bookRepository, LibraryMemberRepository libraryMemberRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.libraryMemberRepository = libraryMemberRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.borrowingRecordRepository.count() > 0;
    }

    @Override
    public String readBorrowingRecordsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importBorrowingRecords() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        BorrowingRecordSeedRootDTO borrowingRecordSeedRootDTO = this.xmlParser.parse(BorrowingRecordSeedRootDTO.class, PATH_INPUT);

        for (BorrowingRecordSeedDTO borrowingRecordSeedDTO : borrowingRecordSeedRootDTO.getBorrowingRecordSeedDTOList()) {
            BookSeedDTO bookSeedDTO = borrowingRecordSeedDTO.getBookSeedDTO();
            LibraryMemberSeedDTO libraryMemberSeedDTO = borrowingRecordSeedDTO.getLibraryMemberSeedDTO();
            Optional<Book> optionalBook = this.bookRepository.findByTitle(bookSeedDTO.getTitle());
            Optional<LibraryMember> libraryMemberOptional = this.libraryMemberRepository.findById(libraryMemberSeedDTO.getId());
            if (!this.validationUtil.isValid(borrowingRecordSeedDTO) || optionalBook.isEmpty() || libraryMemberOptional.isEmpty()) {
                stringBuilder.append("Invalid borrowing record").append(System.lineSeparator());
                continue;
            }
            BorrowingRecord borrowingRecord = this.modelMapper.map(borrowingRecordSeedDTO, BorrowingRecord.class);
            borrowingRecord.setBook(optionalBook.get());
            borrowingRecord.setMember(libraryMemberOptional.get());
            this.borrowingRecordRepository.saveAndFlush(borrowingRecord);
            stringBuilder.append(String.format("Successfully imported borrowing record %s - %s", borrowingRecord.getBook().getTitle(), borrowingRecord.getBorrowDate())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }

    @Override
    public String exportBorrowingRecords() {
        return this.borrowingRecordRepository.findAllByBorrowDateBeforeAndGenreIsOrderByBorrowDateDesc(LocalDate.parse("2021-09-10"), Genre.SCIENCE_FICTION)
                .stream()
                .map(record -> String.format("Book title: %s\n" +
                                "*Book author: %s\n" +
                                "**Date borrowed: %s\n" +
                                "***Borrowed by: %s %s\n",
                        record.getBook().getTitle(),
                        record.getBook().getAuthor(),
                        record.getBorrowDate(),
                        record.getMember().getFirstName(),
                        record.getMember().getLastName()))
                .collect(Collectors.joining());
    }
}
