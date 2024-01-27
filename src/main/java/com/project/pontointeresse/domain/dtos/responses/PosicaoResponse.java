package com.project.pontointeresse.domain.dtos.responses;

import com.project.pontointeresse.domain.dtos.requests.PosicaoRequest;
import com.project.pontointeresse.domain.entities.Posicao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PosicaoResponse {

    private Long id;
    private String placa;
    private LocalDateTime dataPosicao;
    private Integer velocidade;
    private Double longitude;
    private Double latitude;
    private boolean ingnicao;

    public static PosicaoResponse from(Posicao data) {
        return PosicaoResponse.builder()
                .id(data.getId())
                .placa(data.getPlaca())
                .dataPosicao(data.getDataPosicao())
                .velocidade(data.getVelocidade())
                .longitude(data.getLongitude())
                .latitude(data.getLatitude())
                .ingnicao(data.isIngnicao())
                .build();
    }

}
