package com.andersen.countrycity.configuration.swagger;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${api.title}")
    private String title;

    @Value("${api.description}")
    private String description;

    @Value("${api.version}")
    private String version;

    @Value("${api.license.name}")
    private String licenseName;

    @Value("${api.license.url}")
    private String licenseUrl;

    @Value("${api.externalDocs.description}")
    private String externalDocsDescription;

    @Bean
    public OpenAPI springShopOpenAPI() {

        License license = new License().name(licenseName).url(licenseUrl);
        ExternalDocumentation externalDocs = new ExternalDocumentation()
                .description(externalDocsDescription);

        return new OpenAPI()
                .info(new Info().title(title)
                        .description(description)
                        .version(version)
                        .license(license))
                .externalDocs(externalDocs);
    }
}
