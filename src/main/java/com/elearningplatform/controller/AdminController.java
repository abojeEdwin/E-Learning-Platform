package com.elearningplatform.controller;


import com.elearningplatform.dto.request.AdminReq.CreateAdminRequest;
import com.elearningplatform.dto.request.AdminReq.LoginAdminRequest;
import com.elearningplatform.dto.request.AdminReq.SuspendTeacherAccountRequest;
import com.elearningplatform.dto.request.ClientReq.SuspendClientAccountRequest;
import com.elearningplatform.dto.response.AdminRes.CreateAdminResponse;
import com.elearningplatform.dto.response.AdminRes.LoginAdminResponse;
import com.elearningplatform.dto.response.ApiResponse;
import com.elearningplatform.service.AdminServices.AdminServiceImp;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
public class AdminController {

    @Autowired
    private final AdminServiceImp adminServiceImp;

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/create-admin")
    public ResponseEntity<CreateAdminResponse> createAdmin(@Valid @RequestBody CreateAdminRequest request){
        return ResponseEntity.ok(adminServiceImp.createAdmin(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginAdminResponse> login(@Valid @RequestBody LoginAdminRequest request){
        return ResponseEntity.ok(adminServiceImp.login(request));
    }

    @PostMapping("/suspend-client")
    public ResponseEntity<ApiResponse> suspendClient(@Valid @RequestBody SuspendClientAccountRequest request){
        return ResponseEntity.ok(adminServiceImp.suspendClientAccount(request));
    }

    @PostMapping("/reactivate-client")
    public ResponseEntity<ApiResponse> reactivateClient(@Valid @RequestParam Long id){
        return ResponseEntity.ok(adminServiceImp.reactivateClientAccount(id));
    }

    @DeleteMapping("/delete-client")
    public void deleteClient(@Valid @RequestParam Long id){
        adminServiceImp.deleteClientAccount(id);
    }

    @PostMapping("/suspend-teacher")
    public ResponseEntity<ApiResponse> suspendTeacher(@Valid @RequestBody SuspendTeacherAccountRequest request){
        return ResponseEntity.ok(adminServiceImp.suspendTeacherAccount(request));
    }

    @PostMapping("/reactivate-teacher")
    public ResponseEntity<ApiResponse> reactivateTeacher(@Valid @RequestParam Long id){
        return ResponseEntity.ok(adminServiceImp.reactivateTeacherAccount(id));
    }
    @DeleteMapping("/delete-teacher")
    public void deleteTeacher(@Valid @RequestParam Long id){
        adminServiceImp.deleteTeacherAccount(id);
    }

}
