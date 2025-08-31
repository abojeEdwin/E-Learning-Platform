package com.elearningplatform.service;

import com.elearningplatform.dto.request.CreateAdminRequest;
import com.elearningplatform.dto.request.LoginAdminRequest;
import com.elearningplatform.dto.response.CreateAdminResponse;
import com.elearningplatform.dto.response.LoginAdminResponse;

public interface AdminService {

    CreateAdminResponse createAdmin(CreateAdminRequest request);
    LoginAdminResponse login(LoginAdminRequest request);
    void suspendClientAccount(SuspendClientAccountRequest request);
    void reactivateClientAccount(ReactivateClientAccountRequest request);
    void deleteClientAccount(DeleteClientAccountRequest request);
    void suspendTeacherAccount(SuspendTeacherAccountRequest request);
    void reactivateTeacherAccount(ReactivateTeacherAccountRequest request);
    void deleteTeacherAccount(DeleteTeacherAccountRequest request);
}
