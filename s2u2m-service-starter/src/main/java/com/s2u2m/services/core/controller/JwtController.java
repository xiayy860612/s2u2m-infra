package com.s2u2m.services.core.controller;

import com.s2u2m.services.core.security.JwtTokenService;
import com.s2u2m.services.core.security.UserInfo;
import com.s2u2m.services.core.swagger.SwaggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@ConditionalOnProperty(name = "s2u2m.jwt.enableController", havingValue = "true")
@RestController
@RequestMapping("/jwt")
public class JwtController {

    private JwtTokenService jwtTokenService;

    @Autowired
    public void setJwtService(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/")
    public String getJwtToken() throws IOException {
        UserInfo userInfo = new UserInfo();
        userInfo.setUid("1");
        userInfo.setName("test");
        return SwaggerConfig.JWT_AUTH_TOKEN_PREFIX + jwtTokenService.createToken(userInfo);
    }

    @GetMapping("/user-info")
    public UserInfo getUserInfo() {
        return (UserInfo) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }

}
