package com.project.pontointeresse.domain.dtos.requests;

import com.project.pontointeresse.domain.entities.Posicao;
import com.project.pontointeresse.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PosicaoRequest {

    private String placa;
    private String dataPosicao;
    private Integer velocidade;
    private Double longitude;
    private Double latitude;
    private boolean ingnicao;

    public static Posicao from(PosicaoRequest data) {
        return Posicao.builder()
                .placa(data.getPlaca())
                .dataPosicao(DateUtil.obterDataPosicao(data.getDataPosicao()))
                .velocidade(data.getVelocidade())
                .longitude(data.getLongitude())
                .latitude(data.getLatitude())
                .ingnicao(data.isIngnicao())
                .build();
    }

}
