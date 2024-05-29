package com.datawarehouse.model.dtos;


import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FXDealsDto {
    @NotNull(message = "Deal id must not be null")
    @NotEmpty(message = "Deal id must not be empty")
    @Size(max = 255, min = 3, message = "Deal id should be between 3 and 255 character.")
    private String id;

    @NotNull(message = "Ordering currency iso code must not be null")
    @NotEmpty(message = "Ordering currency iso code must not be empty")
    @Size(max = 255, min = 3, message = "Ordering currency iso code should be between 3 and 255 character.")
    private String orderingCurrencyIsoCode;

    @NotNull(message = "To currency iso code must not be null")
    @NotEmpty(message = "To currency iso code must not be empty")
    @Size(max = 255, min = 3, message = "To currency iso code should be between 3 and 255 character.")
    private String toCurrencyIsoCode;

    private LocalDateTime dealTimestamp;

    @NotNull(message = "Deal amount must not be null")
    @Max(value = (long) Double.MAX_VALUE, message = "to long value")
    @Min(value = 0, message = "deal amount should be a positive value.")
    private Double dealAmount;
}
