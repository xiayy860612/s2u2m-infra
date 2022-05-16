package com.s2u2m.services.core.controller;

import com.s2u2m.services.core.security.JwtTokenService;
import com.s2u2m.services.core.security.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.Authentication;
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
        return jwtTokenService.createToken(userInfo);
    }

    @GetMapping("/user-info")
    public UserInfo getUserInfo(Authentication authentication) {
        return (UserInfo) authentication.getDetails();
    }

}
