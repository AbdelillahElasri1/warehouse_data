package com.datawarehouse.service.implementation;

import com.datawarehouse.exceptions.RequestAlreadyExistException;
import com.datawarehouse.model.dtos.FXDealsDto;
import com.datawarehouse.model.entities.FXDeals;
import com.datawarehouse.repository.FXDealsRepository;
import com.datawarehouse.service.FXDealsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FXDealsServiceImplementation implements FXDealsService {
    private final FXDealsRepository fxDealsRepository;
    private final ModelMapper modelMapper;
    @Override
    public FXDealsDto save(FXDealsDto newFXDealsDto) {
        if (fxDealsRepository.existsById(newFXDealsDto.getId())) {
            throw new RequestAlreadyExistException("request already exist");
        }
        return modelMapper.map(
                fxDealsRepository.save(
                        modelMapper.map(newFXDealsDto, FXDeals.class)
                ),
                FXDealsDto.class
        );
    }
}
