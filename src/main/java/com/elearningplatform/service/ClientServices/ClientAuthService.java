package com.elearningplatform.service.ClientServices;

import com.elearningplatform.dto.request.ClientReq.LoginClientRequest;
import com.elearningplatform.dto.request.ClientReq.RegisterClientRequest;
import com.elearningplatform.dto.response.ClientRes.LoginClientResponse;
import com.elearningplatform.dto.response.ClientRes.RegisterClientResponse;

public interface ClientAuthService {

    RegisterClientResponse register(RegisterClientRequest request);
    LoginClientResponse login(LoginClientRequest request);
}
