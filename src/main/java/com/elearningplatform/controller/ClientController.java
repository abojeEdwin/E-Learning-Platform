package com.elearningplatform.controller;


import com.elearningplatform.dto.request.LoginClientRequest;
import com.elearningplatform.dto.request.RegisterClientRequest;
import com.elearningplatform.dto.response.LoginClientResponse;
import com.elearningplatform.dto.response.RegisterClientResponse;
import com.elearningplatform.service.AuthImpl.ClientAuthImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/client-auth")
public class ClientController {

    @Autowired
    private ClientAuthImpl clientAuthService;

    @PostMapping("/register")
    public ResponseEntity<RegisterClientResponse> register(@RequestBody @Valid RegisterClientRequest request){
        return ResponseEntity.ok(clientAuthService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginClientResponse> login (@RequestBody @Valid LoginClientRequest request){
        return ResponseEntity.ok(clientAuthService.login(request));
    }
}
