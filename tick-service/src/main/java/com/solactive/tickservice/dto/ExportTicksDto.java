package com.solactive.tickservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportTicksDto {

    private long exportIndexId;
    private String ric;
    private List<TickDto> ticks;

}