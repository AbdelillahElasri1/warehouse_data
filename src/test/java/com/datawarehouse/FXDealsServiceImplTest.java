package com.datawarehouse;

import com.datawarehouse.exceptions.RequestAlreadyExistException;
import com.datawarehouse.model.dtos.FXDealsDto;
import com.datawarehouse.model.entities.FXDeals;
import com.datawarehouse.repository.FXDealsRepository;
import com.datawarehouse.service.implementation.FXDealsServiceImplementation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FXDealsServiceImplTest {
    @Mock
    private FXDealsRepository fxDealRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FXDealsServiceImplementation underTest;

    private static FXDealsDto fxDealDto;
    private static FXDeals fxDealEntity;

    @BeforeAll
    static void setUp() {
        fxDealDto = new FXDealsDto();
        fxDealEntity = new FXDeals();
    }

    @Test
    @DisplayName("[Create method] Throws already exist Exception.")
    void createMethodShouldThrowRequestAlreadyExistException() {

        fxDealDto.setId("test");

        when(fxDealRepository.existsById(fxDealDto.getId())).thenReturn(true);

        assertThatExceptionOfType(RequestAlreadyExistException.class)
                .isThrownBy(() -> underTest.save(fxDealDto))
                .withMessage("Request already exist.");

        verify(fxDealRepository, times(1)).existsById(fxDealDto.getId());
    }

    @Test
    @DisplayName("[Create method] Returns the inserted entity as FXDealdto.")
    void createMethodShouldReturnTheInsertedDealDto() {

        fxDealDto.setId("test");

        when(fxDealRepository.existsById(fxDealDto.getId())).thenReturn(false);
        when(modelMapper.map(fxDealDto, FXDeals.class)).thenReturn(fxDealEntity);
        when(fxDealRepository.save(fxDealEntity)).thenReturn(fxDealEntity);
        when(modelMapper.map(fxDealEntity, FXDealsDto.class)).thenReturn(fxDealDto);
        assertThat(underTest.save(fxDealDto)).isEqualTo(fxDealDto);

        verify(fxDealRepository, times(1)).existsById(fxDealDto.getId());
        verify(modelMapper, times(1)).map(fxDealDto, FXDeals.class);
        verify(fxDealRepository, times(1)).save(fxDealEntity);
        verify(modelMapper, times(1)).map(fxDealEntity, FXDealsDto.class);
    }
}
