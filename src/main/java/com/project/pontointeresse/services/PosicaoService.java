package com.project.pontointeresse.services;

import com.project.pontointeresse.domain.dtos.requests.PosicaoRequest;
import com.project.pontointeresse.domain.dtos.responses.PosicaoResponse;

public interface PosicaoService {

    PosicaoResponse salvar(PosicaoRequest posicaoRequest);

}
