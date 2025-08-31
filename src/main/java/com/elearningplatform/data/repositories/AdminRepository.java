package com.elearningplatform.data.repositories;


import com.elearningplatform.data.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AdminRepository extends JpaRepository<Admin,Long> {

    Optional<Admin> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Admin findByEmail(String email);

}
