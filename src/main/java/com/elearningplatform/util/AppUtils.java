package com.elearningplatform.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppUtils {

    public static final String USER_NOT_FOUND = "User not found";
    public static final String EMAIL_ALREADY_EXIST = "Email already exist";
    public static final String CLIENT_REGISTERED_SUCCESSFULLY = "Client registered successfully";
    public static final String USERNAME_ALREADY_EXIST = "Username already exist";
    public static final String INVALID_PASSWORD = "Invalid password, please try again";
    public static final String CLIENT_NOT_FOUND = "User not found, please check your credentials";
    public static final String CLIENT_LOGIN_SUCCESSFULLY = "Login successful";
    public static final String TEACHER_NOT_FOUND = "Teacher not found, please check your credentials";
    public static final String TEACHER_LOGIN_SUCCESSFULLY = "Login successful";
    public static final String SUPER_ADMIN_NOT_FOUND = "Super admin not found, please check your credentials";
    public static final String ADMIN_LOGIN_SUCCESSFULLY = "Login successful";
    public static final String ADMIN_NOT_FOUND = "Admin not found, please check your credentials";
    public static final String ADMIN_ALREADY_EXIST =  "Admin already exist, please check your credentials";
    public static final String ILLEGAL_OPERATION = "Illegal operation";
    public static final String CLIENT_SUSPENDED_SUCCESSFULLY = "Client suspended successfully";
    public static final String POST_NOT_FOUND = "Post not found";
    public static final String POST_DELETED_SUCCESSFULLY = "Post deleted successfully";
    public static final String NOT_AUTHORIZED = "You are not authorized to perform this operation";



    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public static String hashPassword(String password){
        return passwordEncoder.encode(password);
    }
    public static boolean verifyPassword(String hashedPassword, String inputPassword) {
        if (hashedPassword == null || hashedPassword.isEmpty() || inputPassword == null || inputPassword.isEmpty()) {return false;}
        try {return passwordEncoder.matches(inputPassword, hashedPassword);} catch (IllegalArgumentException e) {return false;}}



}
