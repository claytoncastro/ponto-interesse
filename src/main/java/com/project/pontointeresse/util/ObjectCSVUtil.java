package com.project.pontointeresse.util;

import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ObjectCSVUtil {

    public static <T> List<T> obterObjetoCSVApi(MultipartFile file, Class<T> classeRetorno) {
        List<String[]> linhas = CSVUtil.obterLinhasCSV(file);
        return obterListaPontosInteresseDoCSVParaSalvar(linhas, classeRetorno);
    }

    private static <T> List<T> obterListaPontosInteresseDoCSVParaSalvar(List<String[]> linhas, Class<T> classeRetorno) {
        try {
            T obj = classeRetorno.getDeclaredConstructor().newInstance();
            List<T> objectResultList = new ArrayList<>();
            boolean isCabecalho = true;

            for (String[] colunas : linhas) {
                int count = 0;
                if(!isCabecalho) {
                    for(String valorColuna : colunas) {
                        Field declaredField = classeRetorno.getDeclaredFields()[count];

                        declaredField.setAccessible(true);
                        declaredField.set(obj, valorColuna);
                        count++;
                    }

                    objectResultList.add(obj);
                }
                isCabecalho = false;
            }
            return objectResultList;

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
