package com.elearningplatform.data.repositories;

import com.elearningplatform.data.enums.Rating;
import com.elearningplatform.data.model.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RatingRepository extends JpaRepository <RatingEntity, Long>{

    List<RatingEntity> findByTeacherId(Long teacherId);
    Optional<RatingEntity> findByTeacherIdAndClientId(Long teacherId, Long clientId);
    boolean existsByTeacherIdAndClientId(Long teacherId, Long clientId);
    Long countByTeacherIdAndRating(Long teacherId, Rating rating);
}
