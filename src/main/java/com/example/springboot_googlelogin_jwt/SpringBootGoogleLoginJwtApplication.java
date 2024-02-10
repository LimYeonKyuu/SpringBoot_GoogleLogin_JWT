package com.example.springboot_googlelogin_jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootGoogleLoginJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGoogleLoginJwtApplication.class, args);
    }

}
