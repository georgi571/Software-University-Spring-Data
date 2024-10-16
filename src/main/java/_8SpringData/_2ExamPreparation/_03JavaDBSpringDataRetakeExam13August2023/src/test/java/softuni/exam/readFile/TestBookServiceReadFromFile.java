package softuni.exam.readFile;
//TestBookServiceReadFromFile

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.service.impl.BookServiceImpl;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class TestBookServiceReadFromFile {

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void readConstellationsFromFile() throws IOException {

        String expected = "[\n" +
                "  {\n" +
                "    \"author\": \"F. Scott Fitzgerald\",\n" +
                "    \"available\": true,\n" +
                "    \"description\": \"A classic novel set in the roaring 20s\",\n" +
                "    \"genre\": \"CLASSIC_LITERATURE\",\n" +
                "    \"title\": \"The Great Gatsby\",\n" +
                "    \"rating\": 9.1\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"F. Scott Fitzgerald\",\n" +
                "    \"available\": true,\n" +
                "    \"description\": \"Romantic bolevard literature\",\n" +
                "    \"genre\": \"CLASSIC_LITERATURE\",\n" +
                "    \"title\": \"Fo\",\n" +
                "    \"rating\": 9.1\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"Harper Lee\",\n" +
                "    \"available\": true,\n" +
                "    \"description\": \"A powerful story addressing racial injustice\",\n" +
                "    \"genre\": \"CLASSIC_LITERATURE\",\n" +
                "    \"title\": \"To Kill a Mockingbird\",\n" +
                "    \"rating\": 5.6\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"Toni Morrison\",\n" +
                "    \"available\": true,\n" +
                "    \"description\": \"Tells the story of a dysfunctional family of formerly enslaved people whose Cincinnati home is haunted by a malevolent spirit.\",\n" +
                "    \"genre\": \"CLASSIC_LITERATURE\",\n" +
                "    \"title\": \"Beloved\",\n" +
                "    \"rating\": -3.1\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"Douglas Adams\",\n" +
                "    \"available\": true,\n" +
                "    \"description\": \"The Hitchhiker's Guide to the Galaxy is a comedy science fiction franchise created by Douglas Adams.\",\n" +
                "    \"genre\": \"SCIENCE_FICTION\",\n" +
                "    \"title\": \"The Hitchhiker's Guide to the Galaxy\",\n" +
                "    \"rating\": 4.2\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"Don Williams\",\n" +
                "    \"available\": true,\n" +
                "    \"description\": \"Some kind of copy of a famous book\",\n" +
                "    \"genre\": \"SCIENCE_FICTION\",\n" +
                "    \"title\": \"The Hitchhiker's Guide to the Galaxy\",\n" +
                "    \"rating\": 3.2\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"George Orwell\",\n" +
                "    \"available\": false,\n" +
                "    \"description\": \"A dystopian novel about totalitarianism\",\n" +
                "    \"genre\": \"SCIENCE_FICTION\",\n" +
                "    \"title\": \"1984\",\n" +
                "    \"rating\": 8.7\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"Joseph Heller\",\n" +
                "    \"available\": true,\n" +
                "    \"description\": \"Often cited as one of the most significant novels of the twentieth century, it uses a distinctive non-chronological third-person omniscient narration, describing events from the points of view of different characters.\",\n" +
                "    \"genre\": \"CLASSIC_LITERATURE\",\n" +
                "    \"title\": \"Catch-22\",\n" +
                "    \"rating\": 9.7\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"J.R.R. Tolkien\",\n" +
                "    \"available\": true,\n" +
                "    \"description\": \"A fantasy adventure about a hobbit's journey\",\n" +
                "    \"genre\": \"FANTASY\",\n" +
                "    \"title\": \"The Hobbit\",\n" +
                "    \"rating\": 4.3\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"J.D. Salinger\",\n" +
                "    \"available\": true,\n" +
                "    \"description\": \"A coming-of-age novel about teenage angst\",\n" +
                "    \"genre\": \"CLASSIC_LITERATURE\",\n" +
                "    \"title\": \"The Catcher in the Rye\",\n" +
                "    \"rating\": 5.1\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"J.K. Rowling\",\n" +
                "    \"available\": true,\n" +
                "    \"description\": \"The first book in the Harry Potter series\",\n" +
                "    \"genre\": \"FANTASY\",\n" +
                "    \"title\": \"Harry Potter and the Sorcerer's Stone\",\n" +
                "    \"rating\": 6.1\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"J.R.R. Tolkien\",\n" +
                "    \"available\": false,\n" +
                "    \"description\": \"An epic fantasy trilogy\",\n" +
                "    \"genre\": \"SCIENCE_FICTION\",\n" +
                "    \"title\": \"The Lord of the Rings\",\n" +
                "    \"rating\": 5.2\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"Aldous Huxley\",\n" +
                "    \"available\": true,\n" +
                "    \"description\": \"A dystopian novel envisioning a future society\",\n" +
                "    \"genre\": \"SCIENCE_FICTION\",\n" +
                "    \"title\": \"Brave New World\",\n" +
                "    \"rating\": 4.7\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"C.S. Lewis\",\n" +
                "    \"available\": true,\n" +
                "    \"description\": \"A series of fantasy novels about Narnia\",\n" +
                "    \"genre\": \"FANTASY\",\n" +
                "    \"title\": \"The Chronicles of Narnia\",\n" +
                "    \"rating\": 5.6\n" +
                "  }\n" +
                "]\n";

        String actual = bookService.readBooksFromFile();

        Assertions.assertEquals(expected, actual);
    }
}