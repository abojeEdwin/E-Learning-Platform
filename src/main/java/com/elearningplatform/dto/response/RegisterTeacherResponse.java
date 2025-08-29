package com.elearningplatform.dto.response;

import com.elearningplatform.data.model.Teacher;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterTeacherResponse {

    private String message;
    private Boolean success;
    private String token;
    private Teacher teacher;
}
