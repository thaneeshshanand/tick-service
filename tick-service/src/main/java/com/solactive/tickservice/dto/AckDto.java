package com.solactive.tickservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AckDto {

    private long exportIndexId;
    private boolean ack;

}