package com.elearningplatform.service.AdminServiceImpl;

import com.elearningplatform.data.model.Admin;
import com.elearningplatform.data.model.Client;
import com.elearningplatform.data.model.Roles;
import com.elearningplatform.data.model.Status;
import com.elearningplatform.data.repositories.AdminRepository;
import com.elearningplatform.data.repositories.ClientRepository;
import com.elearningplatform.dto.request.AdminReq.CreateAdminRequest;
import com.elearningplatform.dto.request.AdminReq.LoginAdminRequest;
import com.elearningplatform.dto.request.AdminReq.SuspendTeacherAccountRequest;
import com.elearningplatform.dto.request.ClientReq.SuspendClientAccountRequest;
import com.elearningplatform.dto.response.AdminRes.CreateAdminResponse;
import com.elearningplatform.dto.response.AdminRes.LoginAdminResponse;
import com.elearningplatform.dto.response.ApiResponse;
import com.elearningplatform.exception.*;
import com.elearningplatform.service.AdminService;
import com.elearningplatform.util.AppUtils;
import com.elearningplatform.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

import static com.elearningplatform.util.AppUtils.*;

@Service
public class AdminServiceImp implements  AdminService {


    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public CreateAdminResponse createAdmin(CreateAdminRequest request) {

        Optional<Admin> foundSuperAdmin = adminRepository.findById(request.getSuperAdminId());
        validateAdmin(request, foundSuperAdmin);

        String hashedPassword = AppUtils.hashPassword(request.getPassword());
        Admin newAdmin = new Admin();
        newAdmin.setEmail(request.getEmail());
        newAdmin.setRole(Roles.ADMIN);
        newAdmin.setUsername(request.getUserName());
        newAdmin.setPassword(hashedPassword);
        Admin savedAdmin = adminRepository.save(newAdmin);

        CreateAdminResponse response = new CreateAdminResponse();
        response.setSuccess(Boolean.TRUE);
        response.setAdmin(savedAdmin);
        return response;

    }

    private void validateAdmin(CreateAdminRequest request, Optional<Admin> foundSuperAdmin) {
        if(foundSuperAdmin.isEmpty()){throw new SuperAdminNotFoundException(SUPER_ADMIN_NOT_FOUND);}
        if(foundSuperAdmin.get().getRole() != Roles.SUPER_ADMIN){throw new IllegalOperationException(ILLEGAL_OPERATION);}
        if(adminRepository.existsByEmail(request.getEmail())){throw new AdminAlreadyExistException(ADMIN_ALREADY_EXIST);}
    }

    @Override
    public LoginAdminResponse login(LoginAdminRequest request) {
        Admin foundAdmin = adminRepository.findByEmail(request.getEmail());
        if(foundAdmin == null){throw new AdminNotFoundException(ADMIN_NOT_FOUND);}
        if(!AppUtils.verifyPassword(foundAdmin.getPassword(),request.getPassword())){
            throw new InvalidPasswordException(INVALID_PASSWORD);}

        String token = jwtUtils.generateToken(foundAdmin);
        LoginAdminResponse response = new LoginAdminResponse();
        response.setAdmin(foundAdmin);
        response.setSuccess(Boolean.TRUE);
        response.setMessage(ADMIN_LOGIN_SUCCESSFULLY);
        response.setToken(token);
        return response;
    }

    @Override
    public ApiResponse suspendClientAccount(SuspendClientAccountRequest request) {
        Optional<Client> foundClient = clientRepository.findById(request.getClientId());
        if(foundClient == null){throw new ClientNotFoundException(CLIENT_NOT_FOUND);}
        foundClient.get().setStatus(Status.SUSPENDED);
        Client suspendedClient = clientRepository.save(foundClient.get());
        return new ApiResponse(Boolean.TRUE,CLIENT_SUSPENDED_SUCCESSFULLY,suspendedClient);
    }

    @Override
    public ApiResponse reactivateClientAccount(Long clientId) {

        Optional<Client> foundClient = clientRepository.findById(clientId);
        if(foundClient == null){throw new ClientNotFoundException(CLIENT_NOT_FOUND);}
        foundClient.get().setStatus(Status.ACTIVE);
        Client suspendedClient = clientRepository.save(foundClient.get());
        return new ApiResponse(Boolean.TRUE,CLIENT_SUSPENDED_SUCCESSFULLY,suspendedClient);
    }

    @Override
    public ApiResponse deleteClientAccount(Long clientId) {
        return null;
    }

    @Override
    public ApiResponse suspendTeacherAccount(SuspendTeacherAccountRequest request) {
        return null;
    }

    @Override
    public ApiResponse reactivateTeacherAccount(Long teacherId) {
        return null;
    }

    @Override
    public ApiResponse deleteTeacherAccount(Long teacherId) {
        return null;
    }
}
