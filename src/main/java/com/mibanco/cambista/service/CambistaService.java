package com.mibanco.cambista.service;

import com.mibanco.cambista.entity.RequestCambista;
import com.mibanco.cambista.entity.ResponseCambista;
import com.mibanco.cambista.entity.ResponseGlobally;
import reactor.core.publisher.Mono;

public interface CambistaService {
    Mono<ResponseGlobally> cambiar(Mono<RequestCambista> c);
}
