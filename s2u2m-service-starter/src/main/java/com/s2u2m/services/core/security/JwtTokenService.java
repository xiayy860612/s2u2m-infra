package com.s2u2m.services.core.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JwtTokenService {
    public static final String PAYLOAD_DETAIL_KEY = "details";

    private final Algorithm algorithm;
    public JwtTokenService(Algorithm algorithm) {
        this.algorithm = Algorithm.HMAC512("SECRET_KEY".getBytes());
    }

    public String createToken(UserInfo userInfo) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String details = mapper.writeValueAsString(userInfo);
        return JWT.create()
                .withSubject(userInfo.getUid())
                .withClaim(PAYLOAD_DETAIL_KEY, details)
                .sign(this.algorithm);
    }

    public UserInfo verify(String token) throws IOException {
        DecodedJWT jwt = JWT.require(this.algorithm)
                .build()
                .verify(token);
        String details = jwt.getClaim(PAYLOAD_DETAIL_KEY).asString();
        return new ObjectMapper()
                .readValue(details, UserInfo.class);
    }
}
