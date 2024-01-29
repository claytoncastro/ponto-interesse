package com.project.pontointeresse.controllers;

import com.project.pontointeresse.domain.dtos.requests.PosicaoRequest;
import com.project.pontointeresse.domain.dtos.responses.PosicaoResponse;
import com.project.pontointeresse.services.PosicaoService;
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

import static com.project.pontointeresse.config.SpringFoxConfig.POSICAO_TAG;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@Api(tags = {POSICAO_TAG})
@RequiredArgsConstructor
@RequestMapping("/v1/posicoes")
public class PosicaoController {

    private final PosicaoService posicaoService;

    @PostMapping
    @ApiOperation(value = "Salva uma posição")
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
    public ResponseEntity<PosicaoResponse> salvar(@RequestBody @Valid PosicaoRequest posicaoRequest) {
        return new ResponseEntity<>(posicaoService.salvar(posicaoRequest), CREATED);
    }

    @PostMapping("/salvar-dados-csv")
    @ApiOperation(
            value = "Salva posições a partir de um arquivo CSV",
            consumes = "multipart/form-data",
            notes = "## Arquivo CSV para Upload\n" +
                    "\n" +
                    "Deve ser inserido um arquivo CSV seguindo o modelo abaixo:\n" +
                    "\n" +
                    "| **placa** | **data_posicao**                                           | **velocidade** | **longitude** | **latitude** | **ignicao** |\n" +
                    "|-----------|------------------------------------------------------------|----------------|---------------|--------------|-------------|\n" +
                    "| TESTE001  | Wed Dec 12 2018 00:04:03 GMT-0200 (Hora oficial do Brasil) | 0              | -51.469891    | -25.3649141  | false       |\n" +
                    "| TESTE002  | Wed Dec 22 2023 10:33:00 GMT-0200 (Hora oficial do Brasil) | 10             | -51.4699098   | -25.3649175  | true        |\n" +
                    "\n" +
                    "A tabela acima foi desenhada para melhor entendimento dos valores para cada coluna, todavia cada linha deve ser escrita em uma única célula. Segue abaixo instruções.\n" +
                    "\n" +
                    "## Instruções" +
                    "\n" +
                    "A célula **A1** deve ser preenchida com os dados abaixo como cabeçalho separados por vírgula:\n" +
                    "```\n" +
                    "placa,data_posicao,velocidade,longitude,latitude,ignicao\n" +
                    "```\n" +
                    "As demais linhas para coluna A (A1, A2, A3, ...) deve ser preenchida com os valores separados por vírgula\n" +
                    "```\n" +
                    "TESTE001,Wed Dec 12 2018 00:04:03 GMT-0200 (Hora oficial do Brasil),0,-51.469891,-25.3649141,false\n" +
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
    public ResponseEntity<Void> salvarPosicoesAPartirDoCSV(
            @ApiParam(required = true, value = "Documento para upload")
            @RequestPart MultipartFile file) {
        posicaoService.salvarPosicoesAPartirDoCSV(file);
        return ResponseEntity.ok().build();
    }

}
