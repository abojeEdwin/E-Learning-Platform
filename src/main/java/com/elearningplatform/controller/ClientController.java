package com.elearningplatform.controller;


import com.elearningplatform.dto.request.ClientReq.LoginClientRequest;
import com.elearningplatform.dto.request.ClientReq.RegisterClientRequest;
import com.elearningplatform.dto.request.ClientReq.UpdateClientProfileRequest;
import com.elearningplatform.dto.response.ApiResponse;
import com.elearningplatform.dto.response.ClientRes.LoginClientResponse;
import com.elearningplatform.dto.response.ClientRes.RegisterClientResponse;
import com.elearningplatform.service.AuthImpl.ClientAuthImpl;;
import com.elearningplatform.service.ClientServices.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/client-auth")
public class ClientController {

    private final ClientAuthImpl clientAuthService;

    @Autowired
    private ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity<RegisterClientResponse> register(@RequestBody @Valid RegisterClientRequest request){
        return ResponseEntity.ok(clientAuthService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginClientResponse> login (@RequestBody @Valid LoginClientRequest loginRequest, HttpServletRequest request){
        return ResponseEntity.ok(clientAuthService.login(loginRequest));
    }

    @PostMapping("/update-profile")
    public ResponseEntity<ApiResponse> updateProfile(@RequestBody @Valid UpdateClientProfileRequest request){
        return ResponseEntity.ok(clientService.updateProfile(request));
    }

    @GetMapping("/view-profile")
    public ResponseEntity<ApiResponse> viewProfile(){
        return ResponseEntity.ok(clientService.viewProfile());
    }
}
