package com.elearningplatform.dto.response.FriendRequestRes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RejectFriendRequestResponse {

    private Boolean success;
    private String message;
    private Object data;
}

