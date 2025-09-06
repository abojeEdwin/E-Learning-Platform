package com.elearningplatform.dto.response.RatingRes;


import com.elearningplatform.data.enums.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RateTeacherResponse {

    private Long id;
    private Rating rating;
    private String comment;
    private LocalDateTime createdAt;
    private Long clientId;
    private Long teacherId;


}
