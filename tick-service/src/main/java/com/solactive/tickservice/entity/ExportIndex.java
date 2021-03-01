package com.solactive.tickservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class ExportIndex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ric;
    private long closingPriceIndex;
    private long previousIndex;
    private ExportStatus status;

    public ExportIndex(String ric, long closingPriceIndex, long previousIndex, ExportStatus status) {
        this.ric = ric;
        this.closingPriceIndex = closingPriceIndex;
        this.previousIndex = previousIndex;
        this.status = status;
    }

}