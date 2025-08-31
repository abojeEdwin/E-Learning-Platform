package com.elearningplatform.dto.request.AdminReq;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReactivateClientAccountRequest {

    @NotNull(message = "This field is required")
    private Long clientId;

}
