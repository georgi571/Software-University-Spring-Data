package softuni.exam.areImported;
//TestDeviceServiceAreImportedFalse

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.repository.DeviceRepository;
import softuni.exam.service.impl.DeviceServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestDeviceServiceAreImportedFalse {


    @InjectMocks
    private DeviceServiceImpl deviceService;
    @Mock
    private DeviceRepository mockDeviceRepository;

    @Test
    void areImportedShouldReturnFalse() {
        Mockito.when(mockDeviceRepository.count()).thenReturn(0L);
        Assertions.assertFalse(deviceService.areImported());
    }
}