package com.elearningplatform.dto.response.AdminRes;

import com.elearningplatform.data.model.Admin;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAdminResponse {

     private String message;
     private Boolean success;
     private Admin admin;

}
