package com.s2u2m.services.core.security;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@AutoConfigureAfter(JwtAuthConfig.class)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class JwtGlobalWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtTokenService jwtTokenService;
    public JwtGlobalWebSecurityConfig(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/swagger-ui/**", "/javainuse-openapi/**", "/v3/api-docs/**").permitAll()
                .antMatchers("/error").permitAll()
                .antMatchers(HttpMethod.POST, "/jwt/").permitAll()
                .anyRequest().authenticated();

        var jwtAuthorizationFilter = new JwtAuthFilter(authenticationManager(), jwtTokenService);
        http.addFilterAfter(jwtAuthorizationFilter, BasicAuthenticationFilter.class);
    }

}
