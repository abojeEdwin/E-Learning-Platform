package com.elearningplatform.dto.response;


import com.elearningplatform.data.model.Client;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterClientResponse {

    private String message;
    private Boolean success;
    private String token;
    private Client client;

}
