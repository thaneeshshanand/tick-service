package com.solactive.tickservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Tick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date timeStamp;
    private double price;
    private double closePrice;
    private String currency;
    private String ric;

    public Tick(Date timeStamp, double price, double closePrice, String currency, String ric) {
        this.timeStamp = timeStamp;
        this.price = price;
        this.closePrice = closePrice;
        this.currency = currency;
        this.ric = ric;
    }

}