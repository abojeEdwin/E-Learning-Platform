package com.elearningplatform.data.repositories;

import com.elearningplatform.data.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

}
