package com.elearningplatform.dto.response;

import com.elearningplatform.data.model.Client;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginClientResponse {

    private String message;
    private Client client;
    private String token;
    private Boolean success;

}
