package com.elearningplatform.service.AuthImpl;



import com.elearningplatform.data.model.Teacher;
import com.elearningplatform.data.repositories.ClientRepository;
import com.elearningplatform.data.repositories.TeacherRepository;
import com.elearningplatform.dto.request.TeacherReq.LoginTeacherRequest;
import com.elearningplatform.dto.request.TeacherReq.RegisterTeacherRequest;
import com.elearningplatform.dto.response.TeacherRes.LoginTeacherResponse;
import com.elearningplatform.dto.response.TeacherRes.RegisterTeacherResponse;
import com.elearningplatform.exception.*;
import com.elearningplatform.service.TeacherAuthService;
import com.elearningplatform.util.AppUtils;
import com.elearningplatform.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.elearningplatform.util.AppUtils.*;

@Service
public class TeacherAuthImpl implements TeacherAuthService {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AppUtils appUtils;
    @Autowired
    private JwtUtils jwtUtils;


    @Override
    public RegisterTeacherResponse register(RegisterTeacherRequest request) {
        if(teacherRepository.existsByEmail(request.getEmail()) || clientRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistException(EMAIL_ALREADY_EXIST);}
        if(teacherRepository.existsByUsername(request.getUserName())){
            throw new UsernameAlreadyExistException(USERNAME_ALREADY_EXIST);}
        String hashedPassword = AppUtils.hashPassword(request.getPassword());

        Teacher teacher = new Teacher();
        teacher.setGender(request.getGender());
        teacher.setEmail(request.getEmail());
        teacher.setRoles(request.getRole());
        teacher.setPassword(hashedPassword);
        teacher.setUsername(request.getUserName());
        teacher.setCreatedAt(LocalDateTime.now());
        teacher.setUpdatedAt(LocalDateTime.now());
        Teacher savedTeacher = teacherRepository.save(teacher);

        String token = jwtUtils.generateToken(savedTeacher);
        RegisterTeacherResponse response = new RegisterTeacherResponse();
        response.setMessage(CLIENT_REGISTERED_SUCCESSFULLY);
        response.setSuccess(Boolean.TRUE);
        response.setToken(token);
        response.setTeacher(savedTeacher);
        return response;

    }

    @Override
    public LoginTeacherResponse login(LoginTeacherRequest loginRequest) {
        Teacher foundTeacher = teacherRepository.findByEmail(loginRequest.getEmail());
        if(!AppUtils.verifyPassword(foundTeacher.getPassword(),loginRequest.getPassword())){
            throw new InvalidPasswordException(INVALID_PASSWORD);}
        if(foundTeacher == null){
            throw new TeacherNotFoundException(TEACHER_NOT_FOUND);}

        String token = jwtUtils.generateToken(foundTeacher);
        LoginTeacherResponse response = new LoginTeacherResponse();
        response.setToken(token);
        response.setSuccess(Boolean.TRUE);
        response.setTeacher(foundTeacher);
        response.setMessage(TEACHER_LOGIN_SUCCESSFULLY);
        return response;
    }
}
