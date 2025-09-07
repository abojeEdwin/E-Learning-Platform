package com.elearningplatform.dto.response.FriendRequestRes;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AcceptFriendRequestResponse{

    private String message;
    private Boolean success;
    private Object data;
}
