package com.project.pontointeresse.services.impl;

import com.project.pontointeresse.domain.dtos.requests.PosicaoRequest;
import com.project.pontointeresse.domain.dtos.responses.PosicaoResponse;
import com.project.pontointeresse.services.PosicaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PosicaoServiceImpl implements PosicaoService {
    @Override
    public PosicaoResponse salvar(PosicaoRequest posicaoRequest) {
        log.info("Inserindo nova posição. . .");
        return null;
    }
}
