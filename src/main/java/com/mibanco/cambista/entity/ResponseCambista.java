package com.mibanco.cambista.entity;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseCambista {
    private String originCurrency;
    private String targetCurrency;
    private Double amount;
    private Float exchangeRate;
    private Double resultAmount;
}
