package com.elearningplatform.controller;


import com.elearningplatform.data.model.RatingEntity;
import com.elearningplatform.dto.request.TeacherReq.RateTeacherRequest;
import com.elearningplatform.dto.request.TeacherReq.UpdateTeacherRequest;
import com.elearningplatform.dto.response.RatingRes.RateTeacherResponse;
import com.elearningplatform.dto.response.RatingRes.RatingSummary;
import com.elearningplatform.service.RatingService.RatingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/rating")
@AllArgsConstructor
public class RatingController {

    private RatingService ratingService;

    @PostMapping("/rate-teacher")
    public ResponseEntity<RateTeacherResponse> rateTeacher(@Valid @RequestBody RateTeacherRequest request) {
        return ResponseEntity.ok(ratingService.rateTeacher(request));
    }

    @GetMapping("/get-rating-summary/{teacherId}/summary")
    public ResponseEntity<RatingSummary> getRatingSummary(@PathVariable Long teacherId) {
        return ResponseEntity.ok(ratingService.getTeacherRatingSummary(teacherId));
    }

    @PutMapping("/teacher/update-rating")
    public ResponseEntity<RateTeacherResponse> updateRating(@Valid @RequestBody UpdateTeacherRequest request) {
        return ResponseEntity.ok(ratingService.updateRating(request));
    }

    @GetMapping("/get-teacher-ratings/{teacherId}")
    public ResponseEntity<List<RateTeacherResponse>> getTeacherRatings(@PathVariable Long teacherId) {
        List<RatingEntity> ratings = ratingService.getTeacherRatings(teacherId);
        List<RateTeacherResponse> responses = ratings.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    private RateTeacherResponse toResponse(RatingEntity rating) {
        return new RateTeacherResponse(
                rating.getId(),
                rating.getRate(),
                rating.getComment(),
                rating.getCreatedAt(),
                rating.getClient().getId(),
                rating.getTeacher().getId());
    }


}
