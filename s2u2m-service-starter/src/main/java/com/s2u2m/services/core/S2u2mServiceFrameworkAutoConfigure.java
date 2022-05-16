package com.s2u2m.services.core;

import com.s2u2m.services.core.error.GlobalExceptionHandler;
import com.s2u2m.services.core.security.JwtAuthConfig;
import com.s2u2m.services.core.swagger.SwaggerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        SwaggerConfig.class,
        JwtAuthConfig.class,
        GlobalExceptionHandler.class
})
public class S2u2mServiceFrameworkAutoConfigure {
}
