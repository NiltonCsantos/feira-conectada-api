package org.feiraconectada.feiraconectadaapi.config.openapiconfig;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI(@Value("${application.version}") final String versaoDaAplicacao) {
        return new OpenAPI()
                .info(new Info().title("FeiraConectada API")
                        .description("API para o gerenciamento das feiras livres em Boquim")
                        .version("v" + versaoDaAplicacao)
                        .license(new License().name("KNTECH Inf 1.0")));
    }

}
