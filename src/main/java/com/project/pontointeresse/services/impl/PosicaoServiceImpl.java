package com.project.pontointeresse.services.impl;

import com.project.pontointeresse.domain.dtos.PosicaoImportacaoCSV;
import com.project.pontointeresse.domain.dtos.requests.PosicaoRequest;
import com.project.pontointeresse.domain.dtos.responses.PosicaoResponse;
import com.project.pontointeresse.domain.entities.Posicao;
import com.project.pontointeresse.repository.PosicaoRepository;
import com.project.pontointeresse.services.PosicaoService;
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
public class PosicaoServiceImpl implements PosicaoService {

    private final PosicaoRepository posicaoRepository;

    @Override
    @Transactional
    public PosicaoResponse salvar(PosicaoRequest posicaoRequest) {
        log.info("Inserindo nova posição. . .");
        Posicao posicaoToSave = PosicaoRequest.from(posicaoRequest);

        return PosicaoResponse
                .from(posicaoRepository.save(posicaoToSave));
    }

    @Override
    @Transactional
    public void salvarPosicoesAPartirDoCSV(MultipartFile file) {
        log.info("Salvando posições a partir do csv. . .");

        List<Posicao> posicoesToSave = ObjectCSVUtil
                .obterObjetoCSVApi(file, PosicaoImportacaoCSV.class)
                .stream()
                .map(PosicaoImportacaoCSV::from)
                .collect(Collectors.toList());

        posicaoRepository.saveAll(posicoesToSave);

        log.info("Posições a partir do CSV salvas com sucesso. . .");
    }

}
