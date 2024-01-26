package com.project.pontointeresse.domain.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PosicaoRequest {

    private String placa;
    private LocalDate dataPosicao;
    private Integer velocidade;
    private Double longitude;
    private Double latitude;
    private boolean ingnicao;

}
