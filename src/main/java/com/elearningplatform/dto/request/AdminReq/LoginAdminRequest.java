package com.elearningplatform.dto.request.AdminReq;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginAdminRequest {

    @Email
    private String email;
    @NotNull(message = "This field is required")
    private String password;


}
