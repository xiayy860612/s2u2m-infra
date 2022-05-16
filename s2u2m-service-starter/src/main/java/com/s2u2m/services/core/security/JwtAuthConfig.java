package com.s2u2m.services.core.security;

import com.auth0.jwt.algorithms.Algorithm;
import com.s2u2m.services.core.controller.JwtController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@EnableConfigurationProperties({JwtAuthProperties.class})
@Configuration
@Import({
        JwtGlobalWebSecurityConfig.class,
        JwtController.class
})
public class JwtAuthConfig {

    @ConditionalOnMissingBean()
    @Bean
    public Algorithm algorithm(JwtAuthProperties properties) {
        return Algorithm.HMAC512(properties.getSecretKey().getBytes());
    }

    @Bean
    public JwtTokenService jwtTokenService(Algorithm algorithm) {
        return new JwtTokenService(algorithm);
    }
}
