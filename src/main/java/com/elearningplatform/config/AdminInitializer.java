package com.elearningplatform.config;

import com.elearningplatform.data.model.Admin;
import com.elearningplatform.data.model.Roles;
import com.elearningplatform.data.repositories.AdminRepository.AdminRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AdminInitializer {

    private final AdminRepository adminRepository;
    private final SecurityConfig config;

    @Value("${app.admin.username}")
    private String userName;
    @Value("${app.admin.email}")
    private String email;
    @Value("${app.admin.password}")
    private String password;
    @Value("${app.admin.role}")
    private String role;


    @Bean
    CommandLineRunner initAdmin() {
        return args -> {
            if (adminRepository.findByUsername(userName).isEmpty()) {
                Admin admin = Admin.builder()
                        .username(userName)
                        .email(email)
                        .password(config.passwordEncoder().encode(password))
                        .roles(Roles.valueOf(role))
                        .build();
                adminRepository.save(admin);
                System.out.println("SuperAdmin created: " + userName);
            } else {
                System.out.println("SuperAdmin already exists with username: " + userName);
            }
        };
    }


}
