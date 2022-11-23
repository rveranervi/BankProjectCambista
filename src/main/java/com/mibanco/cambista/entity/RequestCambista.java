package com.mibanco.cambista.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCambista {
    private String originCurrency;
    private String targetCurrency;
    private Double amount;
}
