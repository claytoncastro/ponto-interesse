package com.project.pontointeresse.services.impl;

import com.project.pontointeresse.domain.dtos.PontoInteresseImportacaoCSV;
import com.project.pontointeresse.domain.dtos.requests.PontoInteresseRequest;
import com.project.pontointeresse.domain.dtos.responses.PontoInteresseResponse;
import com.project.pontointeresse.domain.entities.PontoInteresse;
import com.project.pontointeresse.repository.PontoInteresseRepository;
import com.project.pontointeresse.services.PontoInteresseService;
import com.project.pontointeresse.util.CSVUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    @Transactional
    public void salvarDadosCSV(MultipartFile file) {
        log.info("Salvando pontos de interesse a partir do csv. . .");
        List<String[]> linhas = CSVUtil.obterLinhasCSV(file);
        List<PontoInteresse> pontosInteresseToSave = obterListaPontosInteresseDoCSVParaSalvar(linhas);
        pontoInteresseRepository.saveAll(pontosInteresseToSave);
        log.info("Pontos de interesse a partir do CSV salvas com sucesso. . .");
    }

    private List<PontoInteresse> obterListaPontosInteresseDoCSVParaSalvar(List<String[]> linhas) {
        List<PontoInteresse> posicoes = new ArrayList<>();
        boolean isCabecalho = true;

        for (String[] linha : linhas) {
            if(!isCabecalho) {
                PontoInteresseImportacaoCSV posicao = PontoInteresseImportacaoCSV.builder()
                        .nome(linha[0])
                        .raio(linha[1])
                        .latitude(linha[2])
                        .longitude(linha[3])
                        .build();
                posicoes.add(PontoInteresseImportacaoCSV.from(posicao));
            }
            isCabecalho = false;
        }

        return posicoes;
    }

}
