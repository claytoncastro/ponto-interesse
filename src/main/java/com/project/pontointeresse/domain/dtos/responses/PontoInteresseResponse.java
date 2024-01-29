package com.project.pontointeresse.domain.dtos.responses;

import com.project.pontointeresse.domain.entities.PontoInteresse;
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
public class PontoInteresseResponse {

    private Long id;
    private String nome;
    private Integer raio;
    private Double latitude;
    private Double longitude;

    public static PontoInteresseResponse from(PontoInteresse data) {
        return PontoInteresseResponse.builder()
                .id(data.getId())
                .nome(data.getNome())
                .raio(data.getRaio())
                .latitude(data.getLatitude())
                .longitude(data.getLongitude())
                .build();
    }

}
