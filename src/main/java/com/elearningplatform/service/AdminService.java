package com.elearningplatform.service;

import com.elearningplatform.dto.request.AdminReq.CreateAdminRequest;
import com.elearningplatform.dto.request.AdminReq.DeleteClientAccountRequest;
import com.elearningplatform.dto.request.AdminReq.LoginAdminRequest;
import com.elearningplatform.dto.request.AdminReq.ReactivateClientAccountRequest;
import com.elearningplatform.dto.request.ClientReq.SuspendClientAccountRequest;
import com.elearningplatform.dto.response.ApiResponse;
import com.elearningplatform.dto.response.AdminRes.CreateAdminResponse;
import com.elearningplatform.dto.response.AdminRes.LoginAdminResponse;

public interface AdminService {

    CreateAdminResponse createAdmin(CreateAdminRequest request);
    LoginAdminResponse login(LoginAdminRequest request);
    ApiResponse suspendClientAccount(SuspendClientAccountRequest request);
    ApiResponse reactivateClientAccount(ReactivateClientAccountRequest request);
    ApiResponse deleteClientAccount(DeleteClientAccountRequest request);
    void suspendTeacherAccount(SuspendTeacherAccountRequest request);
    void reactivateTeacherAccount(ReactivateTeacherAccountRequest request);
    void deleteTeacherAccount(DeleteTeacherAccountRequest request);
}
