package com.project.pontointeresse.domain.dtos;

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
public class PontoInteresseImportacaoCSV {

    private String nome;
    private String raio;
    private String latitude;
    private String longitude;

    public static PontoInteresse from(PontoInteresseImportacaoCSV data) {
        return PontoInteresse.builder()
                .nome(data.getNome())
                .raio(Integer.parseInt(data.getRaio()))
                .longitude(Double.parseDouble(data.getLongitude()))
                .latitude(Double.parseDouble(data.getLatitude()))
                .build();
    }

}
