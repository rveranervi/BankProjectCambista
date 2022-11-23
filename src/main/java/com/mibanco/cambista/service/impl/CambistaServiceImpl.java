package com.mibanco.cambista.service.impl;

import com.mibanco.cambista.entity.RequestCambista;
import com.mibanco.cambista.entity.ResponseCambista;
import com.mibanco.cambista.entity.ResponseGlobally;
import com.mibanco.cambista.service.CambistaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Log4j2
public class CambistaServiceImpl implements CambistaService {
    @Override
    public Mono<ResponseGlobally> cambiar(Mono<RequestCambista> c) {
        return c
                .map(this::validateFields)
                .map(this::validateCurrencies)
                .map(this::convertCurrency);
    }

    private ResponseGlobally validateFields(RequestCambista c2) {
        ResponseGlobally res = new ResponseGlobally();
        res.setErrors(new ArrayList<>());
        res.setSuccess(true);
        if(c2.getAmount() == null){
            res.getErrors().add("Se requiere el valor monetario.");
            res.setSuccess(false);
        }
        if(c2.getOriginCurrency() == null){
            res.getErrors().add("Se requiere el valor de la moneda origen.");
            res.setSuccess(false);
        }
        if(c2.getTargetCurrency() == null){
            res.getErrors().add("Se requiere el valor de la moneda destino.");
            res.setSuccess(false);
        }
        res.setBody(
                ResponseCambista.builder()
                        .amount(c2.getAmount())
                        .originCurrency(c2.getOriginCurrency())
                        .targetCurrency(c2.getTargetCurrency())
                        .build()
        );
        return res;
    }

    private ResponseGlobally validateCurrencies(ResponseGlobally c3) {
        if(c3.getErrors().size() == 0) {
            ResponseCambista obj = (ResponseCambista) c3.getBody();
            String[] currencies = {"USD", "EUR", "PEN"};
            if(!Arrays.stream(currencies).anyMatch(obj.getOriginCurrency()::equals)){
                c3.getErrors().add("Moneda origen no válida.");
                c3.setSuccess(false);
            }
            if(!Arrays.stream(currencies).anyMatch(obj.getTargetCurrency()::equals)){
                c3.getErrors().add("Moneda destino no válida.");
                c3.setSuccess(false);
            }
        }
        return c3;
    }

    private ResponseGlobally convertCurrency(ResponseGlobally c4) {
        if(c4.getErrors().size() == 0){
            ResponseCambista obj = (ResponseCambista) c4.getBody();
            switch (obj.getOriginCurrency()){
                case "USD":
                    if(obj.getTargetCurrency().equals("PEN")){
                        obj.setExchangeRate(3.84f);
                    } else if (obj.getTargetCurrency().equals("EUR")) {
                        obj.setExchangeRate(0.97f);
                    }
                    else {}
                    break;
                case "PEN":
                    if(obj.getTargetCurrency().equals("USD")){
                        obj.setExchangeRate(0.26f);
                    } else if (obj.getTargetCurrency().equals("EUR")) {
                        obj.setExchangeRate(0.25f);
                    }
                    else {}
                    break;
                case "EUR":
                    if(obj.getTargetCurrency().equals("PEN")){
                        obj.setExchangeRate(3.96f);
                    } else if (obj.getTargetCurrency().equals("USD")) {
                        obj.setExchangeRate(1.03f);
                    }
                    else {}
                    break;
                default:
                    break;
            }
            obj.setResultAmount(obj.getAmount()*obj.getExchangeRate());
            c4.setBody(obj);
        }
        return c4;
    }

}
