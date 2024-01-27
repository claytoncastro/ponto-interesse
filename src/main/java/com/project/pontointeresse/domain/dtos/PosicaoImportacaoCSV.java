package com.project.pontointeresse.domain.dtos;

import com.project.pontointeresse.domain.entities.Posicao;
import com.project.pontointeresse.exceptions.ParseDataException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static java.util.Locale.ENGLISH;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PosicaoImportacaoCSV {

    private String placa;
    private String dataPosicao;
    private String velocidade;
    private String longitude;
    private String latitude;
    private String ingnicao;

    public static Posicao from(PosicaoImportacaoCSV data) {
        return Posicao.builder()
                .placa(data.placa)
                .dataPosicao(obterDataPosicao(data.dataPosicao))
                .velocidade(Integer.parseInt(data.velocidade))
                .longitude(Double.parseDouble(data.longitude))
                .latitude(Double.parseDouble(data.latitude))
                .ingnicao("true".equals(data.ingnicao))
                .build();
    }

    private static LocalDateTime obterDataPosicao(String dataPosicao) {
        try {
            Date date = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (zzz)", ENGLISH)
                    .parse(dataPosicao);

            return LocalDateTime
                    .ofInstant(date.toInstant(), ZoneId.systemDefault());
        } catch (ParseException ex) {
            throw new ParseDataException("Erro ao converter data '" + dataPosicao + "' na classe PosicaoImportacaoCSV");
        }
    }

}
