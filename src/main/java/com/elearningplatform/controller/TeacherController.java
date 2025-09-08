package com.elearningplatform.controller;

import com.elearningplatform.dto.request.TeacherReq.LoginTeacherRequest;
import com.elearningplatform.dto.request.TeacherReq.RegisterTeacherRequest;
import com.elearningplatform.dto.request.TeacherReq.UpdateTeacherProfileRequest;
import com.elearningplatform.dto.response.ApiResponse;
import com.elearningplatform.dto.response.TeacherRes.LoginTeacherResponse;
import com.elearningplatform.dto.response.TeacherRes.RegisterTeacherResponse;
import com.elearningplatform.service.AuthImpl.TeacherAuthImpl;
import com.elearningplatform.service.TeacherServices.TeacherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/teacher-auth")
public class TeacherController {

    @Autowired
    private TeacherAuthImpl teacherAuthService;
    @Autowired
    private TeacherService teacherService;



    @PostMapping("/register")
    public ResponseEntity<RegisterTeacherResponse> register(@RequestBody @Valid RegisterTeacherRequest request){
        return ResponseEntity.ok(teacherAuthService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginTeacherResponse> login (@RequestBody @Valid LoginTeacherRequest request){
        return ResponseEntity.ok(teacherAuthService.login(request));
    }

    @PostMapping("/update-profile")
    public ResponseEntity<ApiResponse> updateProfile(@RequestBody @Valid UpdateTeacherProfileRequest request){
        return ResponseEntity.ok(teacherService.updateProfile(request));
    }
    @PostMapping("/view-profile")
    public ResponseEntity<ApiResponse> viewProfile(){
        return ResponseEntity.ok(teacherService.viewProfile());
    }

}
