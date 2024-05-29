package com.datawarehouse.service;

import com.datawarehouse.model.dtos.FXDealsDto;

public interface FXDealsService {
    FXDealsDto save(final FXDealsDto newFXDealsDto);
}
