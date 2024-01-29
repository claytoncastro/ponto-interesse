package com.project.pontointeresse.services.impl;

import com.project.pontointeresse.domain.dtos.requests.PontoInteresseRequest;
import com.project.pontointeresse.domain.dtos.responses.PontoInteresseResponse;
import com.project.pontointeresse.domain.entities.PontoInteresse;
import com.project.pontointeresse.repository.PontoInteresseRepository;
import com.project.pontointeresse.services.PontoInteresseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PontoInteresseServiceImpl implements PontoInteresseService {

    private final PontoInteresseRepository pontoInteresseRepository;

    @Override
    public PontoInteresseResponse salvar(PontoInteresseRequest posicaoRequest) {
        log.info("Inserindo novo ponto de interesse. . .");
        PontoInteresse pontoInteresseToSave = PontoInteresseRequest.from(posicaoRequest);

        return PontoInteresseResponse
                .from(pontoInteresseRepository.save(pontoInteresseToSave));
    }

}
