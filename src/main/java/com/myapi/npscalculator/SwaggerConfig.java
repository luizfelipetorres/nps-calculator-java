package com.myapi.npscalculator;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.myapi.npscalculator.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(information());
    }

    private ApiInfo information() {
        return new ApiInfo("NPS CALCULATOR JAVA",
                "Nps calculator made with Java 11, Maven, Spring Boot, Hibernate, Validation, Springfox and Postgres",
                "1",
                "", new Contact("Luiz Felipe Tôrres", "https://github.com/luizfelipetorres", "fellipe116@gmail.com"),
                "No license",
                "",
                Collections.emptyList());
    }
    

}
