package com.solactive.exportservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TickDto {

    private Date timeStamp;
    private double price;
    private double closePrice;
    private String currency;
    private String ric;

}