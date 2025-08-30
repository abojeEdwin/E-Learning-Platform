package com.elearningplatform.controller;

import com.elearningplatform.dto.request.LoginClientRequest;
import com.elearningplatform.dto.request.LoginTeacherRequest;
import com.elearningplatform.dto.request.RegisterTeacherRequest;
import com.elearningplatform.dto.response.LoginClientResponse;
import com.elearningplatform.dto.response.LoginTeacherResponse;
import com.elearningplatform.dto.response.RegisterTeacherResponse;
import com.elearningplatform.service.AuthImpl.TeacherAuthImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/TeacherAuth")
public class TeacherController {

    @Autowired
    private TeacherAuthImpl teacherAuthService;


    @PostMapping("/register")
    public ResponseEntity<RegisterTeacherResponse> register(@RequestBody @Valid RegisterTeacherRequest request){
        return ResponseEntity.ok(teacherAuthService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginTeacherResponse> login (@RequestBody @Valid LoginTeacherRequest request){
        return ResponseEntity.ok(teacherAuthService.login(request));
    }

}
