package com.elearningplatform.dto.request.AdminReq;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuspendTeacherAccountRequest {

    @NotNull(message = "This field is required")
    private String reason;
    @NotNull(message = "This field is required")
    private Long teacherId;

}
