package com.elearningplatform.controller;


import com.elearningplatform.dto.request.AdminReq.CreateAdminRequest;
import com.elearningplatform.dto.request.AdminReq.LoginAdminRequest;
import com.elearningplatform.dto.response.AdminRes.CreateAdminResponse;
import com.elearningplatform.dto.response.AdminRes.LoginAdminResponse;
import com.elearningplatform.service.AdminServiceImpl.AdminServiceImp;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
public class AdminController {

    @Autowired
    private final AdminServiceImp adminServiceImp;

//    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/create-admin")
    public ResponseEntity<CreateAdminResponse> createAdmin(@Valid @RequestBody CreateAdminRequest request){
        return ResponseEntity.ok(adminServiceImp.createAdmin(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginAdminResponse> login(@Valid @RequestBody LoginAdminRequest request){
        return ResponseEntity.ok(adminServiceImp.login(request));
    }


}
