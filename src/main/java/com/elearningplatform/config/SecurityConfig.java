package com.elearningplatform.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {


    private final JwtAuthFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/client-auth/**").permitAll()
                        .requestMatchers("/api/v1/teacher-auth/**").permitAll()
                        .requestMatchers("/api/v1/admin/login").permitAll()
                        .requestMatchers("api/v1/chat/sendMessage").permitAll()
                        .requestMatchers("/api/v1/admin/create-admin").hasAnyAuthority("SUPER_ADMIN")
                        .requestMatchers(
                                "/api/v1/admin/suspend-client",
                                "api/v1/admin/delete-client",
                                "api/v1/admin/reactivate-client",
                                "api/v1/admin/delete-client",
                                "api/v1/admin/suspend-teacher",
                                "api/v1/admin/reactivate-teacher",
                                "api/v1/admin/delete-teacher").hasAnyAuthority("SUPER_ADMIN","ADMIN")
                        .requestMatchers(
                                "api/v1/rating/rate-teacher",
                                "api/v1/rating/teacher/update-rating",
                                "api/v1/rating/get-teacher-ratings",
                                "api/v1/rating/get-rating-summary",
                                "api/v1/posts/create-post",
                                "api/v1/posts/get-all-posts",
                                "api/v1/posts/delete",
                                "api/v1/friendRequest/send-request",
                                "api/v1/friendRequest/accept",
                                "api/v1/friendRequest/reject",
                                "api/v1/friendRequest/pending").hasAnyAuthority("CLIENT","TEACHER")
                        .requestMatchers("/ws/**").permitAll()
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
