package com.elearningplatform.controller;

import com.elearningplatform.data.model.FriendRequest;
import com.elearningplatform.dto.request.FriendRequestReq.SendFriendRequest;
import com.elearningplatform.dto.response.FriendRequestRes.AcceptFriendRequestResponse;
import com.elearningplatform.dto.response.FriendRequestRes.RejectFriendRequestResponse;
import com.elearningplatform.dto.response.FriendRequestRes.SendFriendRequestResponse;
import com.elearningplatform.service.FriendRequestServices.FriendRequestService;
import com.elearningplatform.service.FriendRequestServices.FriendRequestServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/friendRequest")
@RequiredArgsConstructor
public class FriendRequestController {

    @Autowired
    private final FriendRequestServiceImpl friendRequestService;

    @PostMapping("/send-request")
    public ResponseEntity<SendFriendRequestResponse> sendFriendRequest(@Valid @RequestBody SendFriendRequest request){
        return ResponseEntity.ok(friendRequestService.sendFriendRequest(request));
    }

    @PostMapping("/accept/{requestId}")
    public ResponseEntity<AcceptFriendRequestResponse> acceptFriendRequest(@PathVariable Long requestId){
        return ResponseEntity.ok(friendRequestService.acceptFriendRequest(requestId));
    }

    @PostMapping("/reject/{requestId}")
    public ResponseEntity<RejectFriendRequestResponse> rejectRequest(@PathVariable Long requestId) {
        return ResponseEntity.ok(friendRequestService.rejectFriendRequest(requestId));
    }

    @GetMapping("/pending")
    public ResponseEntity<List<FriendRequest>> getPendingRequests() {
        return ResponseEntity.ok(friendRequestService.getPendingRequests());
    }


}
