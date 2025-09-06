package com.elearningplatform.dto.request.TeacherReq;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTeacherProfileRequest {

    @NotNull(message = "This field is required")
    private String fullName;
    @NotNull(message = "This field is required")
    private String address;
    @NotNull(message="This field is required")
    private String location;
    @NotNull(message="This field is required")
    private String phone;

}
