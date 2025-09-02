package com.elearningplatform.data.repositories;

import com.elearningplatform.data.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
