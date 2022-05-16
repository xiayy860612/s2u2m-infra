package com.s2u2m.services.core.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.UUID;

@Data
@ConfigurationProperties(prefix = "s2u2m.jwt")
public class JwtAuthProperties {
    private String secretKey = UUID.randomUUID().toString();
    private Boolean enableController = false;
}
