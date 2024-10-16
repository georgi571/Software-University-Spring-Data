package softuni.exam.areImported;
//TestBookServiceAreImportedFalse

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.repository.BookRepository;
import softuni.exam.service.impl.BookServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestBookServiceAreImportedFalse {

    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookRepository mockBookRepository;

    @Test
    void areImportedShouldReturnFalse() {
        Mockito.when(mockBookRepository.count()).thenReturn(0L);
        Assertions.assertFalse(bookService.areImported());
    }
}