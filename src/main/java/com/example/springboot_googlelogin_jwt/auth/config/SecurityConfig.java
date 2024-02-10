package com.example.springboot_googlelogin_jwt.auth.config;

import com.example.springboot_googlelogin_jwt.auth.filter.ExceptionHandlerFilter;
import com.example.springboot_googlelogin_jwt.auth.filter.JwtTokenFilter;
import com.example.springboot_googlelogin_jwt.auth.service.AuthService;
import com.example.springboot_googlelogin_jwt.member.domain.MemberAuthority;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthService authService;

    @Value("${custom.host.client}")
    private String client;

    @Value("${custom.jwt.secret}")
    private String SECRET_KEY;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new ExceptionHandlerFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtTokenFilter(authService,SECRET_KEY), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                "/api/auth/**","/error"
                        )
                        .permitAll()
                )
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/admin/**")
                        .hasAuthority(MemberAuthority.ADMIN.name())
                )

                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                "/api/**"
                        ).authenticated()
                );
        return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of(client));
        config.setAllowedMethods(Arrays.asList("POST", "GET", "PATCH", "DELETE"));
        config.setAllowedHeaders(Arrays.asList(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}