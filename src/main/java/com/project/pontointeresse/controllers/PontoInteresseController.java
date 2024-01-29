package com.project.pontointeresse.controllers;

import com.project.pontointeresse.domain.dtos.requests.PontoInteresseRequest;
import com.project.pontointeresse.domain.dtos.responses.PontoInteresseResponse;
import com.project.pontointeresse.services.PontoInteresseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import static com.project.pontointeresse.config.SpringFoxConfig.PONTO_INTERESSE_TAG;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@Api(tags = {PONTO_INTERESSE_TAG})
@RequiredArgsConstructor
@RequestMapping("/v1/ponto-interesse")
public class PontoInteresseController {

    private final PontoInteresseService pontoInteresseService;

    @PostMapping
    @ApiOperation(value = "Salva um ponto de interesse")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 201, message = "Created"),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized"),
                    @ApiResponse(code = 403, message = "Forbidden"),
                    @ApiResponse(code = 404, message = "Not Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            })
    public ResponseEntity<PontoInteresseResponse> salvar(@RequestBody @Valid PontoInteresseRequest pontoInteresseRequest) {
        return new ResponseEntity<>(pontoInteresseService.salvar(pontoInteresseRequest), CREATED);
    }

    @PostMapping("/salvar-dados-csv")
    @ApiOperation(
            value = "Salva pontos de interesse a partir de um arquivo CSV",
            consumes = "multipart/form-data",
            notes = "## Arquivo CSV para Upload\n" +
                    "\n" +
                    "Deve ser inserido um arquivo CSV seguindo o modelo abaixo:\n" +
                    "\n" +
                    "| **nome** | **raio** | **latitude**       | **longitude**      |\n" +
                    "|----------|----------|--------------------|--------------------|\n" +
                    "| PONTO 1  | 300      | -25.56742701740896 | -51.47653363645077 |\n" +
                    "| PONTO 2  | 250      | -25.568056         | -51.480278         |\n" +
                    "\n" +
                    "A tabela acima foi desenhada para melhor entendimento dos valores para cada coluna, todavia cada linha deve ser escrita em uma única célula. Segue abaixo instruções.\n" +
                    "\n" +
                    "## Instruções" +
                    "\n" +
                    "A célula **A1** deve ser preenchida com os dados abaixo como cabeçalho separados por vírgula:\n" +
                    "```\n" +
                    "nome,raio,latitude,longitude\n" +
                    "```\n" +
                    "As demais linhas para coluna A (A1, A2, A3, ...) deve ser preenchida com os valores separados por vírgula\n" +
                    "```\n" +
                    "PONTO 1,300,-25.56742701740896,-51.47653363645077\n" +
                    "```")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 201, message = "Created"),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized"),
                    @ApiResponse(code = 403, message = "Forbidden"),
                    @ApiResponse(code = 404, message = "Not Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            })
    public ResponseEntity<Void> salvarDadosCSV(
            @ApiParam(required = true, value = "Document para upload")
            @RequestPart MultipartFile file) {
        pontoInteresseService.salvarDadosCSV(file);
        return ResponseEntity.ok().build();
    }

}
