package com.elearningplatform.service.AdminServiceImpl;

import com.elearningplatform.data.model.*;
import com.elearningplatform.data.repositories.AdminRepository;
import com.elearningplatform.data.repositories.ClientRepository;
import com.elearningplatform.data.repositories.TeacherRepository;
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
    @Autowired
    private TeacherRepository teacherRepository;

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
    public void deleteClientAccount(Long clientId) {
        Optional<Client> foundClient = clientRepository.findById(clientId);
        if(foundClient == null){throw new ClientNotFoundException(CLIENT_NOT_FOUND);}
        clientRepository.deleteById(clientId);
    }

    @Override
    public ApiResponse suspendTeacherAccount(SuspendTeacherAccountRequest request) {
        Optional<Teacher> foundTeacher = teacherRepository.findById(request.getTeacherId());
        if(foundTeacher == null){throw new TeacherNotFoundException(TEACHER_NOT_FOUND);}
        foundTeacher.get().setStatus(Status.SUSPENDED);
        Teacher suspendedTeacher = teacherRepository.save(foundTeacher.get());
        return new ApiResponse(Boolean.TRUE,CLIENT_SUSPENDED_SUCCESSFULLY,suspendedTeacher);
    }

    @Override
    public ApiResponse reactivateTeacherAccount(Long teacherId) {
        Optional<Teacher> foundTeacher = teacherRepository.findById(teacherId);
        if(foundTeacher == null){throw new TeacherNotFoundException(TEACHER_NOT_FOUND);}
        foundTeacher.get().setStatus(Status.ACTIVE);
        Teacher suspendedTeacher = teacherRepository.save(foundTeacher.get());
        return new ApiResponse(Boolean.TRUE,CLIENT_SUSPENDED_SUCCESSFULLY,suspendedTeacher);

    }

    @Override
    public void deleteTeacherAccount(Long teacherId) {
        Optional<Teacher> foundTeacher = teacherRepository.findById(teacherId);
        if(foundTeacher == null){throw new TeacherNotFoundException(TEACHER_NOT_FOUND);}
        teacherRepository.deleteById(teacherId);
    }
}
