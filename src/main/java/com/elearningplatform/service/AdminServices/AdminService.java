package com.elearningplatform.service.AdminServices;

import com.elearningplatform.dto.request.AdminReq.CreateAdminRequest;
import com.elearningplatform.dto.request.AdminReq.LoginAdminRequest;
import com.elearningplatform.dto.request.AdminReq.SuspendTeacherAccountRequest;
import com.elearningplatform.dto.request.ClientReq.SuspendClientAccountRequest;
import com.elearningplatform.dto.response.ApiResponse;
import com.elearningplatform.dto.response.AdminRes.CreateAdminResponse;
import com.elearningplatform.dto.response.AdminRes.LoginAdminResponse;

public interface AdminService {

    CreateAdminResponse createAdmin(CreateAdminRequest request);
    LoginAdminResponse login(LoginAdminRequest request);
    ApiResponse suspendClientAccount(SuspendClientAccountRequest request);
    ApiResponse reactivateClientAccount(Long clientId);
    void deleteClientAccount(Long clientId);
    ApiResponse suspendTeacherAccount(SuspendTeacherAccountRequest request);
    ApiResponse reactivateTeacherAccount(Long teacherId);
    void deleteTeacherAccount(Long teacherId);
}
