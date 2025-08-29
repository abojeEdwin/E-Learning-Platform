package com.elearningplatform.service;

import com.elearningplatform.dto.request.RegisterTeacherRequest;
import com.elearningplatform.dto.response.LoginTeacherResponse;
import com.elearningplatform.dto.response.RegisterTeacherResponse;

public interface TeacherAuthService {
    RegisterTeacherResponse register(RegisterTeacherRequest request);
    LoginTeacherResponse login(LoginTeacherRequest loginRequest);
}
