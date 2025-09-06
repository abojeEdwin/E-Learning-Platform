package com.elearningplatform.dto.request.RatingReq;


import com.elearningplatform.data.enums.Rating;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RateTeacherRequest {

    @NotNull(message = "This field is required")
    Long teacherId;
    @NotNull(message = "This field is required")
    Long clientId;
    @NotNull(message = "This field is required")
    Rating rating;
    @NotNull(message = "This field is required")
    String comment;

}
