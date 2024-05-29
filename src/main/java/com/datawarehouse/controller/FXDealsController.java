package com.datawarehouse.controller;

import com.datawarehouse.model.dtos.FXDealsDto;
import com.datawarehouse.service.FXDealsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/FXDeals")
public class FXDealsController {
    private final FXDealsService fxDealsService;
    @PostMapping
    public ResponseEntity<FXDealsDto> save(@Valid @RequestBody FXDealsDto dealsDto) {
        return new ResponseEntity<>(fxDealsService.save(dealsDto), HttpStatus.CREATED);
    }
}
