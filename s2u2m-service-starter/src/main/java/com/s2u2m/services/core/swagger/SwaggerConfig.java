package com.s2u2m.services.core.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({SwaggerProperties.class})
public class SwaggerConfig {
    public static final String JWT_AUTH_SCHEMA_NAME = "bearerAuth";
    public static final String JWT_SCHEMA = "bearer";
    public static final String JWT_BEARER_FORMAT = "JWT";

    @Bean
    public OpenAPI openAPI(SwaggerProperties properties) {
        Info info = new Info()
                .title(properties.getTitle())
                .version(properties.getVersion());
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(JWT_AUTH_SCHEMA_NAME))
                .components(new Components().addSecuritySchemes(JWT_AUTH_SCHEMA_NAME,
                        new SecurityScheme()
                                .name(JWT_AUTH_SCHEMA_NAME)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme(JWT_SCHEMA)
                                .bearerFormat(JWT_BEARER_FORMAT)
                )).info(info);
    }
}
