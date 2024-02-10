package com.example.springboot_googlelogin_jwt.auth.dto;

import com.example.springboot_googlelogin_jwt.auth.controller.request.LoginRequest;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AuthDto {
    private String googleIdToken;
    private String jwt;

    public static AuthDto from(LoginRequest request) {
        return AuthDto.builder()
                .googleIdToken(request.getGoogleIdToken())
                .build();
    }
}
