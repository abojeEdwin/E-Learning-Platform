package com.elearningplatform.service.TeacherServices;

import com.elearningplatform.data.model.Teacher;
import com.elearningplatform.data.repositories.TeacherRepository;
import com.elearningplatform.dto.request.TeacherReq.UpdateTeacherProfileRequest;
import com.elearningplatform.dto.response.ApiResponse;
import com.elearningplatform.exception.ClientNotFoundException;
import com.elearningplatform.exception.TeacherNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.elearningplatform.util.AppUtils.*;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public ApiResponse updateProfile(UpdateTeacherProfileRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Teacher teacher = teacherRepository.findByUsername(username).orElse(null);
        if (teacher == null) {throw new TeacherNotFoundException(TEACHER_NOT_FOUND);}
        teacher.setFullName(request.getFullName());
        teacher.setAddress(request.getAddress());
        teacher.setPhone(request.getPhone());
        teacher.setLocation(request.getLocation());
        Teacher savedTeacher = teacherRepository.save(teacher);
        return new ApiResponse(Boolean.TRUE, PROFILE_UPDATE_SUCCESSFULLY,savedTeacher);
    }

    @Override
    public ApiResponse viewProfile() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Teacher teacher = teacherRepository.findByUsername(username).orElse(null);
        if (teacher == null) {throw new ClientNotFoundException(TEACHER_NOT_FOUND);}
        return new ApiResponse(Boolean.TRUE, PROFILE_FOUND,teacher);

    }
}
