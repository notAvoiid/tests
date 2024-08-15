package com.abreu.tests.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customAPI() {
        final String LINKED_IN = "https://www.linkedin.com/in/igoranascimento/";
        return new OpenAPI()
                .info(new Info()
                        .title("Product store")
                        .version("v1.0")
                        .description("This API is a CRUD!")
                        .summary("This API is a CRUD!")
                        .termsOfService(LINKED_IN)
                        .contact(new Contact().name("Igor").url(LINKED_IN).email("igorabreu.dev@gmail.com"))
                        .license(new License().name("Apache 2.0").url(LINKED_IN)))
                .externalDocs(new ExternalDocumentation());
    }

}
