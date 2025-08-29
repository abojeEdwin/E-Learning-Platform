package com.elearningplatform.dto.request;


import com.elearningplatform.data.model.Gender;
import com.elearningplatform.data.model.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterClientRequest {

    @Email
    private String email;

    @NotEmpty(message="This field is required")
    private String password;

    @NotEmpty(message="This field is required")
    private String userName;

    @NotEmpty(message="This field is required")
    private Roles role;

    @NotNull(message="This field is required")
    private Gender gender;
}
