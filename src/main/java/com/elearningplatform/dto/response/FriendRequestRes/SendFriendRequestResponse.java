package com.elearningplatform.dto.response.FriendRequestRes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendFriendRequestResponse {

    private String message;
    private Boolean success;
    private Object data;

}
