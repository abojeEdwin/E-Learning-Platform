package com.elearningplatform.service.RatingService;

import com.elearningplatform.data.enums.Rating;
import com.elearningplatform.data.model.Client;
import com.elearningplatform.data.model.RatingEntity;
import com.elearningplatform.data.model.Teacher;
import com.elearningplatform.data.repositories.ClientRepository;
import com.elearningplatform.data.repositories.RatingRepository;
import com.elearningplatform.data.repositories.TeacherRepository;
import com.elearningplatform.dto.request.TeacherReq.RateTeacherRequest;
import com.elearningplatform.dto.request.TeacherReq.UpdateTeacherRequest;
import com.elearningplatform.dto.response.RatingRes.RateTeacherResponse;
import com.elearningplatform.dto.response.RatingRes.RatingSummary;
import com.elearningplatform.exception.RatingNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.elearningplatform.util.AppUtils.RATING_NOT_FOUND;
import static com.elearningplatform.util.AppUtils.TEACHER_ALREADY_RATED;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TeacherRepository teacherRepository;


    @Override
    public RateTeacherResponse rateTeacher(RateTeacherRequest request) {

        Optional<Teacher> teacher = teacherRepository.findById(request.getTeacherId());
        Optional<Client> client = clientRepository.findById(request.getClientId());

        if (ratingRepository.existsByTeacherIdAndClientId(request.getTeacherId(), request.getClientId())) {
            throw new IllegalStateException(TEACHER_ALREADY_RATED);}

        RatingEntity ratingEntity = new RatingEntity();
        ratingEntity.setRate(request.getRating());
        ratingEntity.setTeacher(teacher.get());
        ratingEntity.setClient(client.get());
        ratingEntity.setComment(request.getComment());
        ratingEntity.setCreatedAt(LocalDateTime.now());
        RatingEntity savedEntity = ratingRepository.save(ratingEntity);
        return new RateTeacherResponse(
                savedEntity.getId(),
                savedEntity.getRate(),
                savedEntity.getComment(),
                savedEntity.getCreatedAt(),
                savedEntity.getClient().getId(),
                savedEntity.getTeacher().getId());
    }

    @Override
    public RateTeacherResponse updateRating(UpdateTeacherRequest request) {
        RatingEntity rating = ratingRepository.findById(request.getRatingId())
                .orElseThrow(() -> new RatingNotFoundException(RATING_NOT_FOUND));
        rating.setRate(request.getNewRating());
        rating.setComment(request.getNewComment());
        ratingRepository.save(rating);
        return new RateTeacherResponse(
                rating.getId(),
                rating.getRate(),
                rating.getComment(),
                rating.getCreatedAt(),
                rating.getClient().getId(),
                rating.getTeacher().getId());
    }

    @Override
    public RatingSummary getTeacherRatingSummary(Long teacherId) {
        Double averageRating = ratingRepository.findAverageRatingByTeacherId(teacherId);
        Long totalRatings = ratingRepository.countByTeacherId(teacherId);
        Map<Rating, Long> ratingDistribution = new EnumMap<>(Rating.class);
        for (Rating rating : Rating.values()) {
            Long count = ratingRepository.countByTeacherIdAndRating(teacherId, rating);
            ratingDistribution.put(rating, count);
        }
        return new RatingSummary(averageRating, totalRatings, ratingDistribution);
    }

    @Override
    public List<RatingEntity> getTeacherRatings(Long teacherId) {
        return ratingRepository.findByTeacherId(teacherId);
    }

    @Override
    public boolean hasClientRatedTeacher(Long teacherId, Long clientId) {
        return ratingRepository.existsByTeacherIdAndClientId(teacherId, clientId);
    }

}
