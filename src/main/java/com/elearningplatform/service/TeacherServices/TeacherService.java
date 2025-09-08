package com.elearningplatform.service.TeacherServices;


import com.elearningplatform.data.model.Teacher;
import com.elearningplatform.dto.request.TeacherReq.UpdateTeacherProfileRequest;
import com.elearningplatform.dto.response.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {

    ApiResponse updateProfile(UpdateTeacherProfileRequest request);
    ApiResponse viewProfile();
//    void receiveNotification(Long id);
//    void sendReminder(Long id);
//    void removeFriend(Long id);

}
