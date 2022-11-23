package com.mibanco.cambista.entity;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseGlobally {
    private boolean success;
    private List<String> errors;
    private Object body;
}
