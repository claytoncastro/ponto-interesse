package com.project.pontointeresse.domain.dtos.requests;

import com.project.pontointeresse.domain.entities.Posicao;
import com.project.pontointeresse.util.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Essa é placa do veículo" , example = "TESTE002")
    private String placa;
    @NotBlank(message = "O campo 'dataPosicao' é obrigatório!")
    @Schema(description = "Essa é data da posição do veículo" , example = "Wed Dec 12 2018 00:04:03 GMT-0200 (Hora oficial do Brasil)")
    private String dataPosicao;
    @NotNull(message = "O campo 'velocidade' é obrigatório!")
    @Schema(description = "Essa é a velocidade do veículo" , example = "10")
    private Integer velocidade;
    @NotNull(message = "O campo 'longitude' é obrigatório!")
    @Schema(description = "Essa é a longitude do veículo" , example = "-51.469891")
    private Double longitude;
    @NotNull(message = "O campo 'latitude' é obrigatório!")
    @Schema(description = "Essa é a latitude do veículo" , example = "-25.3649141")
    private Double latitude;
    @NotNull(message = "O campo 'ingnicao' é obrigatório!")
    @Schema(description = "Esse campo indica se o veículo está ligado ou não" , example = "true")
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
