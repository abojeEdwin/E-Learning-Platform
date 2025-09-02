package com.elearningplatform.controller;


import com.elearningplatform.data.enums.Roles;
import com.elearningplatform.data.repositories.ClientRepository;
import com.elearningplatform.dto.request.ClientReq.LoginClientRequest;
import com.elearningplatform.dto.request.ClientReq.RegisterClientRequest;
import com.elearningplatform.dto.response.ClientRes.LoginClientResponse;
import com.elearningplatform.dto.response.ClientRes.RegisterClientResponse;
import com.elearningplatform.service.AuthImpl.ClientAuthImpl;
import com.elearningplatform.util.RateLimiter;
import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
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
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private final RateLimiter rateLimiter;

    @PostMapping("/register")
    public ResponseEntity<RegisterClientResponse> register(@RequestBody @Valid RegisterClientRequest request){
        return ResponseEntity.ok(clientAuthService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginClientResponse> login (@RequestBody @Valid LoginClientRequest loginRequest, HttpServletRequest request){

            String ip = request.getRemoteAddr();
            System.out.println("Client IP: " + ip);
            Bucket bucket = rateLimiter.resolveBucket(ip, Roles.CLIENT.toString());

            if(!bucket.tryConsume(1)){
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(clientAuthService.login(loginRequest));

    }
}
