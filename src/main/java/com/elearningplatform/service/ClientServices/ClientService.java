package com.elearningplatform.service.ClientServices;

import com.elearningplatform.dto.request.ClientReq.UpdateClientProfileRequest;
import com.elearningplatform.dto.response.ApiResponse;

public interface ClientService {

    ApiResponse updateProfile (UpdateClientProfileRequest request);
    ApiResponse viewProfile();
//    void removeFriend(Long userId);
//    void receiveNotification(Long id);
//    void sendReminder(Long id);

}
