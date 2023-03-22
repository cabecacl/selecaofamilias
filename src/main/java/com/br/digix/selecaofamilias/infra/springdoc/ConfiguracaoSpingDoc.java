package com.br.digix.selecaofamilias.infra.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracaoSpingDoc {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Seleção de Familias")
                        .description("API Rest para seleção de familias. Projeto vinculado a DIGIX desafio técnico")
                        .contact(new Contact()
                                .name("Cleiton de Aguiar")
                                .email("cleitonaguiarsilva@gmail.com"))
                        );
    }
}
