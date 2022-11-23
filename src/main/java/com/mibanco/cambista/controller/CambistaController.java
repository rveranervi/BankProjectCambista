package com.mibanco.cambista.controller;

import com.mibanco.cambista.entity.RequestCambista;
import com.mibanco.cambista.entity.ResponseGlobally;
import com.mibanco.cambista.service.CambistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CambistaController {
    @Autowired
    CambistaService service;

    @GetMapping
    public String welcome() {
        return "Bienvenido Cambista API 0.0.1";
    }

    @PostMapping(value = "/transform",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseGlobally> cambiar(@RequestBody RequestCambista request){
        return service.cambiar(Mono.just(request));
    }
}
