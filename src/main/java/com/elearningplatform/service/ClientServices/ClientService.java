package com.elearningplatform.service.ClientServices;

import com.elearningplatform.data.model.Lesson;
import com.elearningplatform.dto.request.ClientReq.UpdateClientProfileRequest;
import com.elearningplatform.dto.request.FriendRequestReq.SendFriendRequest;
import com.elearningplatform.dto.request.LessonReq.BookLessonRequest;
import com.elearningplatform.dto.request.PostReq.CreatePostRequest;
import com.elearningplatform.dto.request.TeacherReq.RateTeacherRequest;
import com.elearningplatform.dto.response.ApiResponse;
import com.elearningplatform.dto.response.FriendRequestRes.AcceptFriendRequestResponse;
import com.elearningplatform.dto.response.FriendRequestRes.RejectFriendRequestResponse;
import com.elearningplatform.dto.response.FriendRequestRes.SendFriendRequestResponse;
import com.elearningplatform.dto.response.PostRes.CreatePostResponse;
import com.elearningplatform.dto.response.RatingRes.RateTeacherResponse;

public interface ClientService {

    ApiResponse updateProfile (UpdateClientProfileRequest request);
    CreatePostResponse createPost(CreatePostRequest request);
    void deletePost(Long id);
    ApiResponse viewProfile();
    RateTeacherResponse rateTeacher(RateTeacherRequest request);
    Lesson bookLesson(BookLessonRequest request);
    Lesson videoCall(Long id);
    SendFriendRequestResponse sendFriendRequest(SendFriendRequest request);
    AcceptFriendRequestResponse acceptFriendRequest(Long requestId);
    RejectFriendRequestResponse declineFriendRequest(Long requestId);
//    void removeFriend(Long userId);
//    void receiveNotification(Long id);
//    void sendReminder(Long id);

}
