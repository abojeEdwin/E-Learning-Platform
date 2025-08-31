package com.elearningplatform.data.repositories;

import com.elearningplatform.data.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository <Client, Long>{
    Client findByEmail(String email);
    Optional<Client> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<Client> findById(Long id);
}
