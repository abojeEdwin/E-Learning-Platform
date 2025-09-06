package com.elearningplatform.dto.request.RatingReq;

import com.elearningplatform.data.enums.Rating;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTeacherRequest {

    @NotNull(message = "This field is required")
    Long ratingId;
    @NotNull(message = "This field is required")
    Rating newRating;
    @NotNull(message = "This field is required")
    String newComment;

}
