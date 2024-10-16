package softuni.exam.areImported;
//TestBookServiceAreImportedTrue

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
public class TestBookServiceAreImportedTrue {

    @InjectMocks
    private BookServiceImpl constellationService;
    @Mock
    private BookRepository mockBookRepository;

    @Test
    void areImportedShouldReturnTrue() {
        Mockito.when(mockBookRepository.count()).thenReturn(1L);
        Assertions.assertTrue(constellationService.areImported());
    }
}