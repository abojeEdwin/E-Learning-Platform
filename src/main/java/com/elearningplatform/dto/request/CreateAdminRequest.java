package com.elearningplatform.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAdminRequest {

    @Email
    private String email;
    @NotNull(message = "This field is required")
    private String password;
    @NotNull(message = "This field is required")
    private String userName;
}
