package com.elearningplatform.dto.request.ClientReq;


import com.elearningplatform.data.enums.Rating;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RateTeacherRequest {

    @NotNull(message = "This field is required")
    private Rating rating;

    @NotNull
    private Long teacherId;

    @NotNull
    private Long clientId;

    @NotNull(message = "This field is required")
    private String comment;


}
