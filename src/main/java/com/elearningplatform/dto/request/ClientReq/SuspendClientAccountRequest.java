package com.elearningplatform.dto.request.ClientReq;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuspendClientAccountRequest {

    @NotNull(message = "This field is required")
    private String reason;
    @NotNull(message = "This field is required")
    private Long clientId;

}
