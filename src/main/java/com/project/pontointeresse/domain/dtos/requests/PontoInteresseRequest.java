package com.project.pontointeresse.domain.dtos.requests;

import com.project.pontointeresse.domain.entities.PontoInteresse;
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
public class PontoInteresseRequest {

    @NotBlank(message = "O campo 'nome' é obrigatório!")
    @Schema(description = "Esse é o nome do ponto de interesse" , example = "PONTO 1")
    private String nome;
    @NotNull(message = "O campo 'raio' é obrigatório!")
    @Schema(description = "Esse é o raio do ponto de interesse" , example = "300")
    private Integer raio;
    @NotNull(message = "O campo 'latitude' é obrigatório!")
    @Schema(description = "Esse é a latitude do ponto de interesse" , example = "-25.56742701740896")
    private Double latitude;
    @NotNull(message = "O campo 'longitude' é obrigatório!")
    @Schema(description = "Esse é a longitude do ponto de interesse" , example = "-51.47653363645077")
    private Double longitude;

    public static PontoInteresse from(PontoInteresseRequest data) {
        return PontoInteresse.builder()
                .nome(data.getNome())
                .raio(data.getRaio())
                .latitude(data.getLatitude())
                .longitude(data.getLongitude())
                .build();
    }

}
