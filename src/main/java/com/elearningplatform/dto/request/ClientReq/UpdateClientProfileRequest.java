package com.elearningplatform.dto.request.ClientReq;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateClientProfileRequest {

//    private Long clientId;
    @NotNull(message = "This field is required")
    private String fullName;
    @NotNull(message = "This field is required")
    private String address;
    @NotNull(message="This field is required")
    private String location;
    @NotNull(message="This field is required")
    private String phone;

}
