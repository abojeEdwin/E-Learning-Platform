package com.elearningplatform.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppUtils {

    public static final String USER_NOT_FOUND = "User not found";
    public static final String EMAIL_ALREADY_EXIST = "Email already exist";
    public static final String CLIENT_REGISTERED_SUCCESSFULLY = "Client registered successfully";
    public static final String USERNAME_ALREADY_EXIST = "Username already exist";

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public static String hashPassword(String password){
        return passwordEncoder.encode(password);
    }
    public static boolean verifyPassword(String hashedPassword, String inputPassword) {
        if (hashedPassword == null || hashedPassword.isEmpty() || inputPassword == null || inputPassword.isEmpty()) {return false;}
        try {return passwordEncoder.matches(inputPassword, hashedPassword);} catch (IllegalArgumentException e) {return false;}}



}
