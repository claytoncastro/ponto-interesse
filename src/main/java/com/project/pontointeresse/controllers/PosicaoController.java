package com.project.pontointeresse.controllers;

import com.project.pontointeresse.domain.dtos.requests.PosicaoRequest;
import com.project.pontointeresse.domain.dtos.responses.PosicaoResponse;
import com.project.pontointeresse.services.PosicaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/posicoes")
public class PosicaoController {

    private final PosicaoService posicaoService;

    public PosicaoController(PosicaoService posicaoService) {
        this.posicaoService = posicaoService;
    }

    @PostMapping
    public ResponseEntity<PosicaoResponse> salvar(@RequestBody PosicaoRequest posicaoRequest) {
        return new ResponseEntity<>(posicaoService.salvar(posicaoRequest), CREATED);
    }

}
