package com.project.pontointeresse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    public static final String POSICAO_TAG = "Endpoints de Posição";

    @Bean
    public Docket apiConfigDocs() {
        return new Docket(SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.project.pontointeresse.controllers"))
                .paths(PathSelectors.any())
                .build()
                .tags(
                        new Tag(POSICAO_TAG, "Controller de Posição")
                )
                .apiInfo(infoDocs());
    }

    private ApiInfo infoDocs() {
        return new ApiInfo(
                "Api - Posição",
                "API de Posição",
                "1.0.0",
                "Terms",
                null,
                "Apache License",
                "URL",
                new ArrayList<>());
    }

}