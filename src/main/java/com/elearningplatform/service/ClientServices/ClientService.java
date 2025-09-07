package com.elearningplatform.service.ClientServices;

import com.elearningplatform.data.model.Client;
import com.elearningplatform.data.model.Lesson;
import com.elearningplatform.dto.request.ClientReq.RateTeacherRequest;
import com.elearningplatform.dto.request.ClientReq.UpdateClientProfileRequest;
import com.elearningplatform.dto.request.PostReq.CreatePostRequest;
import com.elearningplatform.dto.response.ApiResponse;
import com.elearningplatform.dto.response.PostRes.CreatePostResponse;

public interface ClientService {

    ApiResponse updateProfile (UpdateClientProfileRequest request);
    CreatePostResponse createPost(CreatePostRequest request);
    void deletePost(Long id);
    Client viewProfile(Long id);
    RateTeacherRequest rateTeacher(RateTeacherRequest request);
    void bookLesson(Lesson lesson);
    void videoCall(Long id);
    void receiveNotification(Long id);
    void sendReminder(Long id);
    void sendFriendRequest(Long id);
    void acceptFriendRequest(Long id);
    void declineFriendRequest(Long id);
    void removeFriend(Long id);

}
