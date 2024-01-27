package com.project.pontointeresse.util;

import com.project.pontointeresse.exceptions.ParseDataException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static java.util.Locale.ENGLISH;

public abstract class DateUtil {

    public static LocalDateTime obterDataPosicao(String dataPosicao) {
        try {
            Date date = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", ENGLISH)
                    .parse(dataPosicao);
            return LocalDateTime
                    .ofInstant(date.toInstant(), ZoneId.systemDefault());
        } catch (ParseException ex) {
            throw new ParseDataException("Erro ao converter data '" + dataPosicao + "'");
        }
    }

}
