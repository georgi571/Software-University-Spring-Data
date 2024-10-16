package softuni.exam.areImported;
//TestLibraryMemberServiceAreImportedFalse

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
public class TestLibraryMemberServiceAreImportedFalse {

    @InjectMocks
    private LibraryMemberServiceImpl libraryMemberService;
    @Mock
    private LibraryMemberRepository mockLibraryMemberRepository;

    @Test
    void areImportedShouldReturnFalse() {
        Mockito.when(mockLibraryMemberRepository.count()).thenReturn(0L);
        Assertions.assertFalse(libraryMemberService.areImported());
    }
}