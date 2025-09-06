package com.elearningplatform.service.TeacherServices;


import com.elearningplatform.data.model.Teacher;
import com.elearningplatform.dto.request.PostReq.CreatePostRequest;
import com.elearningplatform.dto.request.TeacherReq.UpdateTeacherProfileRequest;
import com.elearningplatform.dto.response.ApiResponse;
import com.elearningplatform.dto.response.PostRes.CreatePostResponse;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {

    ApiResponse updateProfile(UpdateTeacherProfileRequest request);
    CreatePostResponse createPost(CreatePostRequest request);
    void deletePost(Long id);
    Teacher viewProfile(Long id);
    void videoCall(Long id);
    void receiveNotification(Long id);
    void sendReminder(Long id);
    void sendFriendRequest(Long id);
    void acceptFriendRequest(Long id);
    void declineFriendRequest(Long id);
    void removeFriend(Long id);

}
