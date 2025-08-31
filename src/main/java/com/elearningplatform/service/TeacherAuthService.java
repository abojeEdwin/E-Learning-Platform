package com.elearningplatform.service;

import com.elearningplatform.dto.request.TeacherReq.LoginTeacherRequest;
import com.elearningplatform.dto.request.TeacherReq.RegisterTeacherRequest;
import com.elearningplatform.dto.response.TeacherRes.LoginTeacherResponse;
import com.elearningplatform.dto.response.TeacherRes.RegisterTeacherResponse;

public interface TeacherAuthService {
    RegisterTeacherResponse register(RegisterTeacherRequest request);
    LoginTeacherResponse login(LoginTeacherRequest loginRequest);
}
