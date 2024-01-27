package com.project.pontointeresse.domain.dtos.requests;

import com.project.pontointeresse.domain.entities.Posicao;
import com.project.pontointeresse.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PosicaoRequest {

    @NotBlank(message = "O campo 'placa' é obrigatório!")
    private String placa;
    @NotBlank(message = "O campo 'dataPosicao' é obrigatório!")
    private String dataPosicao;
    @NotNull(message = "O campo 'velocidade' é obrigatório!")
    private Integer velocidade;
    @NotNull(message = "O campo 'longitude' é obrigatório!")
    private Double longitude;
    @NotNull(message = "O campo 'latitude' é obrigatório!")
    private Double latitude;
    @NotNull(message = "O campo 'ingnicao' é obrigatório!")
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
