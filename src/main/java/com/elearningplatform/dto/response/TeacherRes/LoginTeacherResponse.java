package com.elearningplatform.dto.response.TeacherRes;


import com.elearningplatform.data.model.Teacher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginTeacherResponse {

    private String message;
    private Teacher teacher;
    private String token;
    private Boolean success;
}
