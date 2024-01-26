package com.project.pontointeresse.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class CSVUtil {

    public static List<String[]> obterLinhasCSV(MultipartFile file) {
        List<String[]> linhas = new ArrayList<>();
        if (file.isEmpty()) {
            log.info("Por favor, selecione um arquivo CSV.");
        }

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            linhas = csvReader.readAll();
            log.info("Arquivo CSV enviado com sucesso!");
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            log.info("Falha ao processar o arquivo CSV.");
        }
        return linhas;
    }

}
