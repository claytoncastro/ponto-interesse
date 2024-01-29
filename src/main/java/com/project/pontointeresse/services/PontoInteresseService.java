package com.project.pontointeresse.services;

import com.project.pontointeresse.domain.dtos.requests.PontoInteresseRequest;
import com.project.pontointeresse.domain.dtos.responses.PontoInteresseResponse;
import org.springframework.web.multipart.MultipartFile;

public interface PontoInteresseService {

    PontoInteresseResponse salvar(PontoInteresseRequest posicaoRequest);
    void salvarDadosCSV(MultipartFile file);

}
