package com.project.pontointeresse.services;

import com.project.pontointeresse.domain.dtos.requests.PosicaoRequest;
import com.project.pontointeresse.domain.dtos.responses.PosicaoResponse;
import org.springframework.web.multipart.MultipartFile;

public interface PosicaoService {

    PosicaoResponse salvar(PosicaoRequest posicaoRequest);
    void salvarDadosCSV(MultipartFile file);

}
