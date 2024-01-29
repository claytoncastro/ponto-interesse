package com.project.pontointeresse.services;

import com.project.pontointeresse.domain.dtos.requests.PontoInteresseRequest;
import com.project.pontointeresse.domain.dtos.responses.PontoInteresseResponse;

public interface PontoInteresseService {

    PontoInteresseResponse salvar(PontoInteresseRequest posicaoRequest);

}
