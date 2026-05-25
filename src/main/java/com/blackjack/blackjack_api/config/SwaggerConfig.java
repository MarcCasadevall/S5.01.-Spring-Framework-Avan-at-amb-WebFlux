package com.blackjack.blackjack_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Blackjack API")
                        .version("1.0.0")
                        .description("Reactive API for playing Blackjack with Spring WebFlux")
                        .contact(new Contact()
                                .name("Marc")
                                .email("apple5.92@hotmail.com")
                        )
                );
    }
}