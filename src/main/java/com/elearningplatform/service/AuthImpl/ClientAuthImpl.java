package com.elearningplatform.service.AuthImpl;


import com.elearningplatform.data.model.Client;
import com.elearningplatform.data.repositories.ClientRepository;
import com.elearningplatform.data.repositories.TeacherRepository;
import com.elearningplatform.dto.request.ClientReq.LoginClientRequest;
import com.elearningplatform.dto.request.ClientReq.RegisterClientRequest;
import com.elearningplatform.dto.response.ClientRes.LoginClientResponse;
import com.elearningplatform.dto.response.ClientRes.RegisterClientResponse;
import com.elearningplatform.exception.ClientNotFoundException;
import com.elearningplatform.exception.EmailAlreadyExistException;
import com.elearningplatform.exception.InvalidPasswordException;
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
    private TeacherRepository teacherRepository;
    @Autowired
    private AppUtils appUtils;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public RegisterClientResponse register(RegisterClientRequest request) {
        if(clientRepository.existsByEmail(request.getEmail()) || teacherRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistException(EMAIL_ALREADY_EXIST);}
        if(clientRepository.existsByUsername(request.getUserName())){
            throw new UsernameAlreadyExistException(USERNAME_ALREADY_EXIST);}
        String hashedPassword = AppUtils.hashPassword(request.getPassword());

        Client client = new Client();
        client.setEmail(request.getEmail());
        client.setRoles(request.getRole());
        client.setPassword(hashedPassword);
        client.setGender(request.getGender());
        client.setUsername(request.getUserName());
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
        Client foundClient = clientRepository.findByEmail(request.getEmail());
        if(!AppUtils.verifyPassword(foundClient.getPassword(),request.getPassword())){
            throw new InvalidPasswordException(INVALID_PASSWORD);}
        if(foundClient == null){
            throw new ClientNotFoundException(CLIENT_NOT_FOUND);}

        String token = jwtUtils.generateToken(foundClient);
        LoginClientResponse response = new LoginClientResponse();
        response.setToken(token);
        response.setSuccess(Boolean.TRUE);
        response.setClient(foundClient);
        response.setMessage(CLIENT_LOGIN_SUCCESSFULLY);
        return response;
    }
}
