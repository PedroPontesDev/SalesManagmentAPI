package com.devPontes.api.v1.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
				.title("RESTful API with Java 17 and Spring Boot 3")
				.version("v1")
				.description("Api para gerenciar estoque, vendedores e vendas!")
				.termsOfService("htpps://www.com.br/my-term")
		        .license(new License().name("My fucking license")
		        .url("https://wwww.com.br/my-license"))
		        );	
		
	}

}