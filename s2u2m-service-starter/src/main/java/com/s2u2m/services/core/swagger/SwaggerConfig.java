package com.s2u2m.services.core.swagger;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;

@EnableOpenApi
@Configuration
@EnableConfigurationProperties({SwaggerProperties.class})
public class SwaggerConfig {
    public static final String JWT_AUTH_SCHEMA_NAME = "JWT";
    public static final String JWT_AUTH_KEY_IN_HEADER = "Authorization";
    public static final String JWT_AUTH_KEY_POSITION = "header";
    public static final String JWT_AUTH_TOKEN_PREFIX = "Bearer ";
    public static final String[] JWT_HOST_URLS = new String[] {
            "/swagger-*/**", "/javainuse-openapi/**",
            "/v3/api-docs/**",
            "/v2/api-docs/**"
    };


    @Bean
    public Docket docket(SwaggerProperties properties) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(properties))
                .securityContexts(List.of(securityContext()))
                .securitySchemes(List.of(apiKey()))
                .enable(true)
                .select()
                .apis(basePackage("com.s2u2m.services")
                        .and(withClassAnnotation(Controller.class)
                                .or(withClassAnnotation(RestController.class))))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey(JWT_AUTH_SCHEMA_NAME, JWT_AUTH_KEY_IN_HEADER, JWT_AUTH_KEY_POSITION);
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope scope = new AuthorizationScope("global", "access all");
        AuthorizationScope[] scopes = new AuthorizationScope[1];
        scopes[0] = scope;
        return List.of(new SecurityReference(JWT_AUTH_SCHEMA_NAME, scopes));
    }

    private ApiInfo apiInfo(SwaggerProperties properties) {
        return new ApiInfoBuilder()
                .title(properties.getTitle())
                .version(properties.getVersion())
                .build();
    }
}
