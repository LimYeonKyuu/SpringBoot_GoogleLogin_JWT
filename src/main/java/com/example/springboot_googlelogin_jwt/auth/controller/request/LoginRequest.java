package com.example.springboot_googlelogin_jwt.auth.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@Getter
public class LoginRequest {
    private String googleIdToken;
}
