package softuni.exam.areImported;
//TestLibraryMemberServiceAreImportedTrue

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.impl.LibraryMemberServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestLibraryMemberServiceAreImportedTrue {

    @InjectMocks
    private LibraryMemberServiceImpl libraryMemberService;
    @Mock
    private LibraryMemberRepository mockLibraryMemberRepository;

    @Test
    void areImportedShouldReturnTrue() {
        Mockito.when(mockLibraryMemberRepository.count()).thenReturn(1L);
        Assertions.assertTrue(libraryMemberService.areImported());
    }
}