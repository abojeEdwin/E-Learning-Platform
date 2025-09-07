package com.elearningplatform.dto.request.FriendRequestReq;

import com.elearningplatform.data.enums.Roles;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendFriendRequest {

    @NotNull(message = "This field is required")
    private Long receiverId;
    @NotNull(message = "This field is required")
    private Roles receiverRole;

}
