package com.elearningplatform.service.ClientServices;

import com.elearningplatform.data.model.Client;
import com.elearningplatform.dto.request.ClientReq.UpdateClientProfileRequest;
import com.elearningplatform.dto.request.PostReq.CreatePostRequest;
import com.elearningplatform.dto.response.ApiResponse;
import com.elearningplatform.dto.response.PostRes.CreatePostResponse;

public interface ClientService {

    ApiResponse updateProfile (UpdateClientProfileRequest request);
    CreatePostResponse createPost(CreatePostRequest request);
    Client viewProfile(String id);
    void rateTeacher(RateTeacherRequest request);



}
