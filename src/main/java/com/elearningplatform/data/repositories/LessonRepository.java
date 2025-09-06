package com.elearningplatform.data.repositories;

import com.elearningplatform.data.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository <Lesson, Long>{

}
