package com.elearningplatform.service.FriendRequestServices;

import com.elearningplatform.data.model.FriendRequest;
import com.elearningplatform.dto.request.FriendRequestReq.SendFriendRequest;
import com.elearningplatform.dto.response.FriendRequestRes.AcceptFriendRequestResponse;
import com.elearningplatform.dto.response.FriendRequestRes.RejectFriendRequestResponse;
import com.elearningplatform.dto.response.FriendRequestRes.SendFriendRequestResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FriendRequestService {

    SendFriendRequestResponse sendFriendRequest(SendFriendRequest request);
    AcceptFriendRequestResponse acceptFriendRequest(Long requestId);
    RejectFriendRequestResponse rejectFriendRequest(Long requestId);
    List<FriendRequest> getPendingRequests();

}
