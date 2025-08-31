package com.elearningplatform.dto.response.ClientRes;


import com.elearningplatform.data.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterClientResponse {

    private String message;
    private Boolean success;
    private String token;
    private Client client;

}
