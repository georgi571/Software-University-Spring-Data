package softuni.exam.areImported;
//TestSaleServiceAreImportedFalse

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.repository.SaleRepository;
import softuni.exam.service.impl.SaleServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestSaleServiceAreImportedFalse {


    @InjectMocks
    private SaleServiceImpl saleService;
    @Mock
    private SaleRepository mockSaleRepository;

    @Test
    void areImportedShouldReturnFalse() {
        Mockito.when(mockSaleRepository.count()).thenReturn(0L);
        Assertions.assertFalse(saleService.areImported());
    }
}