package com.project.pontointeresse.services.impl;

import com.project.pontointeresse.domain.dtos.PosicaoImportacaoCSV;
import com.project.pontointeresse.domain.dtos.requests.PosicaoRequest;
import com.project.pontointeresse.domain.dtos.responses.PosicaoResponse;
import com.project.pontointeresse.domain.entities.Posicao;
import com.project.pontointeresse.repository.PosicaoRepository;
import com.project.pontointeresse.services.PosicaoService;
import com.project.pontointeresse.util.CSVUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PosicaoServiceImpl implements PosicaoService {

    private final PosicaoRepository posicaoRepository;

    public PosicaoServiceImpl(PosicaoRepository posicaoRepository) {
        this.posicaoRepository = posicaoRepository;
    }

    @Override
    @Transactional
    public PosicaoResponse salvar(PosicaoRequest posicaoRequest) {
        log.info("Inserindo nova posição. . .");
        return null;
    }

    @Override
    public void salvarDadosCSV(MultipartFile file) {
        log.info("Salvando posições a partir do csv. . .");
        List<String[]> linhas = CSVUtil.obterLinhasCSV(file);
        List<Posicao> posicoes = obterListaPosicaoDoCSVParaSalvar(linhas);
        posicaoRepository.saveAll(posicoes);
        log.info("Posições a partir do csv salvas com sucesso. . .");
    }

    private List<Posicao> obterListaPosicaoDoCSVParaSalvar(List<String[]> linhas) {
        List<Posicao> posicoes = new ArrayList<>();
        boolean isCabecalho = true;

        for (String[] linha : linhas) {
            if(!isCabecalho) {
                PosicaoImportacaoCSV posicao = PosicaoImportacaoCSV.builder()
                        .placa(linha[0])
                        .dataPosicao(linha[1])
                        .velocidade(linha[2])
                        .longitude(linha[3])
                        .latitude(linha[4])
                        .ingnicao(linha[5])
                        .build();
                posicoes.add(PosicaoImportacaoCSV.from(posicao));
            }
            isCabecalho = false;
        }

        return posicoes;
    }
}
