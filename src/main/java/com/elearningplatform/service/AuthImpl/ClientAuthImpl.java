package com.elearningplatform.service.AuthImpl;


import com.elearningplatform.data.model.Client;
import com.elearningplatform.data.repositories.ClientRepository;
import com.elearningplatform.dto.request.LoginClientRequest;
import com.elearningplatform.dto.request.RegisterClientRequest;
import com.elearningplatform.dto.response.LoginClientResponse;
import com.elearningplatform.dto.response.RegisterClientResponse;
import com.elearningplatform.exception.EmailAlreadyExistException;
import com.elearningplatform.exception.UsernameAlreadyExistException;
import com.elearningplatform.service.ClientAuthService;
import com.elearningplatform.util.AppUtils;
import com.elearningplatform.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.elearningplatform.util.AppUtils.*;

@Service
public class ClientAuthImpl implements ClientAuthService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AppUtils appUtils;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public RegisterClientResponse register(RegisterClientRequest request) {
        if(clientRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistException(EMAIL_ALREADY_EXIST);}
        if(clientRepository.existsByUsername(request.getUserName())){
            throw new UsernameAlreadyExistException(USERNAME_ALREADY_EXIST);}
        String hashedPassword = AppUtils.hashPassword(request.getPassword());

        Client client = new Client();
        client.setEmail(request.getEmail());
        client.setRoles(request.getRole());
        client.setPassword(hashedPassword);
        client.setGender(request.getGender());
        client.setCreatedAt(LocalDateTime.now());
        client.setUpdatedAt(LocalDateTime.now());
        Client savedClient = clientRepository.save(client);

        String token = jwtUtils.generateToken(savedClient);
        RegisterClientResponse response = new RegisterClientResponse();
        response.setMessage(CLIENT_REGISTERED_SUCCESSFULLY);
        response.setSuccess(Boolean.TRUE);
        response.setToken(token);
        response.setClient(savedClient);
        return response;

    }

    @Override
    public LoginClientResponse login(LoginClientRequest request) {

        return null;
    }
}
