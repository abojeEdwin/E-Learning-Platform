package com.elearningplatform.data.repositories;

import com.elearningplatform.data.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository <Teacher, Long>{
    Teacher findByEmail(String email);
    Optional<Teacher> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
