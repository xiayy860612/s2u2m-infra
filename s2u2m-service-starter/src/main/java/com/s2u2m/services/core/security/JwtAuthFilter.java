package com.s2u2m.services.core.security;


import com.s2u2m.services.core.swagger.SwaggerConfig;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

public class JwtAuthFilter extends BasicAuthenticationFilter {
    private JwtTokenService jwtTokenService;

    public JwtAuthFilter(
            AuthenticationManager authenticationManager,
            JwtTokenService jwtTokenService) {
        super(authenticationManager);
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    protected void doFilterInternal
            (HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String token = request.getHeader(SwaggerConfig.JWT_AUTH_KEY_IN_HEADER);
        if (!StringUtils.hasText(token) || !token.startsWith(SwaggerConfig.JWT_AUTH_TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        token = token.replace(SwaggerConfig.JWT_AUTH_TOKEN_PREFIX, "").trim();
        UserInfo userInfo = jwtTokenService.verify(token);
        // TODO: get authorities
        Collection<GrantedAuthority> authorities = Collections.emptyList();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userInfo.getName(), null, authorities);
        authenticationToken.setDetails(userInfo);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }
}
