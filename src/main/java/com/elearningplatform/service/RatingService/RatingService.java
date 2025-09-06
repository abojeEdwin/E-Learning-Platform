package com.elearningplatform.service.RatingService;

import com.elearningplatform.data.model.RatingEntity;
import com.elearningplatform.dto.request.RatingReq.RateTeacherRequest;
import com.elearningplatform.dto.request.RatingReq.UpdateTeacherRequest;
import com.elearningplatform.dto.response.RatingRes.RatingSummary;

import java.util.List;

public interface RatingService {
    RatingEntity rateTeacher(RateTeacherRequest request);
    RatingEntity updateRating(UpdateTeacherRequest request);
    RatingSummary getTeacherRatingSummary(Long teacherId);
    List<RatingEntity> getTeacherRatings(Long teacherId);
    boolean hasClientRatedTeacher(Long teacherId, Long clientId);

}
