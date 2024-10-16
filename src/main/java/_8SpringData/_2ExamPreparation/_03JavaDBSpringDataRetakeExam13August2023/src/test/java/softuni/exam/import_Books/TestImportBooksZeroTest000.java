package softuni.exam.import_Books;
//TestImportBooksZeroTest000

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import softuni.exam.service.impl.BookServiceImpl;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportBooksZeroTest000 {

    @Autowired
    private BookServiceImpl bookService;

    @Test
    void importConstellationsZeroTest() throws IOException {
        String actual = bookService.importBooks();

        String expected = "Successfully imported book F. Scott Fitzgerald - The Great Gatsby\n" +
                "Invalid book\n" +
                "Successfully imported book Harper Lee - To Kill a Mockingbird\n" +
                "Invalid book\n" +
                "Successfully imported book Douglas Adams - The Hitchhiker's Guide to the Galaxy\n" +
                "Invalid book\n" +
                "Successfully imported book George Orwell - 1984\n" +
                "Successfully imported book Joseph Heller - Catch-22\n" +
                "Successfully imported book J.R.R. Tolkien - The Hobbit\n" +
                "Successfully imported book J.D. Salinger - The Catcher in the Rye\n" +
                "Successfully imported book J.K. Rowling - Harry Potter and the Sorcerer's Stone\n" +
                "Successfully imported book J.R.R. Tolkien - The Lord of the Rings\n" +
                "Successfully imported book Aldous Huxley - Brave New World\n" +
                "Successfully imported book C.S. Lewis - The Chronicles of Narnia";

        String[] actualSplit = actual.split("\\r\\n?|\\n");
        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        Assertions.assertArrayEquals(expectedSplit,actualSplit);
    }



}
