package com.elearningplatform.service;

import com.elearningplatform.dto.request.LoginClientRequest;
import com.elearningplatform.dto.request.RegisterClientRequest;
import com.elearningplatform.dto.response.LoginClientResponse;
import com.elearningplatform.dto.response.RegisterClientResponse;

public interface ClientAuthService {

    RegisterClientResponse register(RegisterClientRequest request);
    LoginClientResponse login(LoginClientRequest request);
}
