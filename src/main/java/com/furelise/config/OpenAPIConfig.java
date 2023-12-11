package com.furelise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;


@Configuration
public class OpenAPIConfig {
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Furelise REST API",
                "Alice schlenderte durch den Garten, fühlte die sanfte Brise, die ihr zartes Kleid streichelte, und genoss die Ruhe der Natur.",
                "1.0",
                "Terms of Service",
                new Contact("Fühlte", "furelise.com.tw", "fühlte@gmail.com"),
                "MIT License",
                "https://opensource.org/licenses/MIT",
                Collections.emptyList()
        );
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}