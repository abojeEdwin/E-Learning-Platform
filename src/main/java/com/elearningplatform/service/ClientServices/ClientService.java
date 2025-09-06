package com.elearningplatform.service.ClientServices;

import com.elearningplatform.data.model.Client;
import com.elearningplatform.dto.request.ClientReq.RateTeacherRequest;
import com.elearningplatform.dto.request.ClientReq.UpdateClientProfileRequest;
import com.elearningplatform.dto.request.PostReq.CreatePostRequest;
import com.elearningplatform.dto.response.ApiResponse;
import com.elearningplatform.dto.response.ClientRes.RatingResponse;
import com.elearningplatform.dto.response.PostRes.CreatePostResponse;

public interface ClientService {

    ApiResponse updateProfile (UpdateClientProfileRequest request);
    CreatePostResponse createPost(CreatePostRequest request);
    Client viewProfile(String id);
    RatingResponse rateTeacher(RateTeacherRequest request);
    void videoCall(String id);
    void receiveNotification(String id);
    void sendReminder(String id);
    void sendFriendRequest(String id);
    void acceptFriendRequest(String id);
    void declineFriendRequest(String id);
    void removeFriend(String id);


}
