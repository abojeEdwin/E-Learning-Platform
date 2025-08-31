package com.elearningplatform.dto.request.TeacherReq;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginTeacherRequest {

    @Email
    private String email;
    @NotNull
    private String password;
}
