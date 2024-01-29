package com.project.pontointeresse.controllers;

import com.project.pontointeresse.domain.dtos.requests.PontoInteresseRequest;
import com.project.pontointeresse.domain.dtos.responses.PontoInteresseResponse;
import com.project.pontointeresse.services.PontoInteresseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
