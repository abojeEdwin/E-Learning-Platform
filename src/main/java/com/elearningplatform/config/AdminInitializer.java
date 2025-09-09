package com.elearningplatform.config;

import com.elearningplatform.data.model.Admin;
import com.elearningplatform.data.enums.Roles;
import com.elearningplatform.data.repositories.AdminRepository;

import lombok.RequiredArgsConstructor;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;;

@Configuration
@RequiredArgsConstructor
public class AdminInitializer {

    private final AdminRepository adminRepository;
    private final ApplicationConfig config;

    @Value("${app.admin.username}")
    private String userName;
    @Value("${app.admin.email}")
    private String email;
    @Value("${app.admin.password}")
    private String password;
    @Value("${app.admin.role}")
    private Roles role;


    @Bean
    CommandLineRunner initAdmin() {
        return args -> {
            if (adminRepository.findByUsername(userName).isEmpty()) {
                Admin admin = Admin.builder()
                        .username(userName)
                        .email(email)
                        .password(config.passwordEncoder().encode(password))
                        .role(Roles.SUPER_ADMIN)
                        .build();
                adminRepository.save(admin);
                System.out.println("SuperAdmin created: " + userName);
            } else {
                System.out.println("SuperAdmin already exists with username: " + userName);
            }
        };
    }


}
