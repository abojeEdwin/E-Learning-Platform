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


    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public static String hashPassword(String password){
        return passwordEncoder.encode(password);
    }
    public static boolean verifyPassword(String hashedPassword, String inputPassword) {
        if (hashedPassword == null || hashedPassword.isEmpty() || inputPassword == null || inputPassword.isEmpty()) {return false;}
        try {return passwordEncoder.matches(inputPassword, hashedPassword);} catch (IllegalArgumentException e) {return false;}}



}
