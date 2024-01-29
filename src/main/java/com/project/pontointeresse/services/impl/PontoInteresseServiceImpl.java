package com.project.pontointeresse.services.impl;

import com.project.pontointeresse.domain.dtos.PontoInteresseImportacaoCSV;
import com.project.pontointeresse.domain.dtos.requests.PontoInteresseRequest;
import com.project.pontointeresse.domain.dtos.responses.PontoInteresseResponse;
import com.project.pontointeresse.domain.entities.PontoInteresse;
import com.project.pontointeresse.repository.PontoInteresseRepository;
import com.project.pontointeresse.services.PontoInteresseService;
import com.project.pontointeresse.util.ObjectCSVUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PontoInteresseServiceImpl implements PontoInteresseService {

    private final PontoInteresseRepository pontoInteresseRepository;

    @Override
    @Transactional
    public PontoInteresseResponse salvar(PontoInteresseRequest posicaoRequest) {
        log.info("Inserindo novo ponto de interesse. . .");
        PontoInteresse pontoInteresseToSave = PontoInteresseRequest.from(posicaoRequest);

        return PontoInteresseResponse
                .from(pontoInteresseRepository.save(pontoInteresseToSave));
    }

    @Override
    @Transactional
    public void salvarPontosInteresseAPartirDoCSV(MultipartFile file) {
        log.info("Salvando pontos de interesse a partir do csv. . .");

        List<PontoInteresse> pontosInteresseToSave = ObjectCSVUtil
                .obterObjetoCSVApi(file, PontoInteresseImportacaoCSV.class)
                .stream()
                .map(PontoInteresseImportacaoCSV::from)
                .collect(Collectors.toList());

        pontoInteresseRepository.saveAll(pontosInteresseToSave);

        log.info("Pontos de interesse a partir do CSV salvas com sucesso. . .");
    }

}
