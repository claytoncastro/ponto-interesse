package com.project.pontointeresse.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.project.pontointeresse.exceptions.FileIsEmptyException;
import com.project.pontointeresse.exceptions.IOCsvException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class CSVUtil {

    public static List<String[]> obterLinhasCSV(MultipartFile file) {
        if (file.isEmpty()) throw new FileIsEmptyException("Por favor, selecione um arquivo CSV.");

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            return csvReader.readAll();
        } catch (IOException | CsvException e) {
            throw new IOCsvException("Erro ao tentar ler linhas do arquivo CSV");
        }
    }

}
