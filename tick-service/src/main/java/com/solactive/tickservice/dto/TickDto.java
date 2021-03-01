package com.solactive.tickservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class TickDto {

    private final Date timeStamp;
    private final double price;
    private final double closePrice;
    private final String currency;
    private final String ric;

}