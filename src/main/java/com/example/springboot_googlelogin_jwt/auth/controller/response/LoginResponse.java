package com.example.springboot_googlelogin_jwt.auth.controller.response;

import com.example.springboot_googlelogin_jwt.auth.dto.AuthDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private String jwt;
    public static LoginResponse from(AuthDto dto) {
        return LoginResponse.builder()
                .jwt(dto.getJwt())
                .build();
    }
}
