package com.elearningplatform.service.RatingService;

import com.elearningplatform.data.model.RatingEntity;
import com.elearningplatform.dto.request.TeacherReq.RateTeacherRequest;
import com.elearningplatform.dto.request.TeacherReq.UpdateTeacherRequest;
import com.elearningplatform.dto.response.RatingRes.RateTeacherResponse;

import java.util.List;

public interface RatingService {

    RateTeacherResponse rateTeacher(RateTeacherRequest request);
    RateTeacherResponse updateRating(UpdateTeacherRequest request);
    List<RatingEntity> getTeacherRatings(Long teacherId);
    boolean hasClientRatedTeacher(Long teacherId, Long clientId);

}
