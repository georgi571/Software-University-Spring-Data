package softuni.exam.areImported;
//TestSellerServiceAreImportedFalse

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.impl.SellerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestSellerServiceAreImportedFalse {

    @InjectMocks
    private SellerServiceImpl sellerService;
    @Mock
    private SellerRepository mockSellerRepository;

    @Test
    void areImportedShouldReturnFalse() {
        Mockito.when(mockSellerRepository.count()).thenReturn(0L);
        Assertions.assertFalse(sellerService.areImported());
    }
}