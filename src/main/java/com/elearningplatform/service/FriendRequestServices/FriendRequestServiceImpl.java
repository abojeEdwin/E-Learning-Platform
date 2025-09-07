package com.elearningplatform.service.FriendRequestServices;

import com.elearningplatform.data.enums.RequestStatus;
import com.elearningplatform.data.enums.Roles;
import com.elearningplatform.data.model.Client;
import com.elearningplatform.data.model.FriendRequest;
import com.elearningplatform.data.model.Teacher;
import com.elearningplatform.data.repositories.ClientRepository;
import com.elearningplatform.data.repositories.FriendRequestRepository;
import com.elearningplatform.data.repositories.TeacherRepository;
import com.elearningplatform.dto.request.FriendRequestReq.SendFriendRequest;
import com.elearningplatform.dto.response.FriendRequestRes.AcceptFriendRequestResponse;
import com.elearningplatform.dto.response.FriendRequestRes.RejectFriendRequestResponse;
import com.elearningplatform.dto.response.FriendRequestRes.SendFriendRequestResponse;
import com.elearningplatform.exception.FriendRequestAlreadyExistException;
import com.elearningplatform.exception.FriendRequestNotFound;
import com.elearningplatform.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.elearningplatform.util.AppUtils.*;

@Service
@RequiredArgsConstructor
public class FriendRequestServiceImpl implements FriendRequestService {

    @Autowired
    private final FriendRequestRepository friendRequestRepository;
    @Autowired
    private final ClientRepository clientRepository;
    @Autowired
    private final TeacherRepository teacherRepository;
    private final SimpMessagingTemplate messagingTemplate;



    @Override
    public SendFriendRequestResponse sendFriendRequest(SendFriendRequest request) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Client client = clientRepository.findByUsername(username).orElse(null);
        Teacher teacher = teacherRepository.findByUsername(username).orElse(null);
        if (client == null && teacher == null) {
            throw new UserNotFoundException(USER_NOT_FOUND);}

        Long senderId;
        Roles senderType;

        if (client != null) {
            senderId = client.getId();
            senderType = Roles.CLIENT;
        } else {
            senderId = teacher.getId();
            senderType = Roles.TEACHER;}

        friendRequestRepository.findBySenderAndReceiverIdAndRoles(
                senderId, request.getReceiverId(), senderType, request.getReceiverRole()
        ).ifPresent(req -> {
            throw new FriendRequestAlreadyExistException(FRIEND_ALREADY_EXIST);
        });

        FriendRequest createRequest = FriendRequest.builder()
                .senderId(senderId)
                .senderRole(senderType)
                .receiverId(request.getReceiverId())
                .receiverRole(request.getReceiverRole())
                .status(RequestStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        FriendRequest savedRequest = friendRequestRepository.save(createRequest);

        messagingTemplate.convertAndSend(
                "/topic/notifications/" + request.getReceiverId(),
                username + " sent you a friend request");

        return new SendFriendRequestResponse(FRIEND_REQUEST_SENT,Boolean.TRUE,savedRequest);
    }

    @Override
    public AcceptFriendRequestResponse acceptFriendRequest(Long requestId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        FriendRequest request = friendRequestRepository.findById(requestId)
                .orElseThrow(() -> new FriendRequestNotFound(FRIEND_REQUEST_NOT_FOUND));

        request.setStatus(RequestStatus.ACCEPTED);
        friendRequestRepository.save(request);

        messagingTemplate.convertAndSend(
                "/topic/notifications/" + request.getReceiverId(),
                username + " accepted your friend request");

        return new AcceptFriendRequestResponse(FRIEND_REQUEST_ACCEPTED,Boolean.TRUE,request);
    }

    @Override
    public RejectFriendRequestResponse rejectFriendRequest(Long requestId) {
        FriendRequest request = friendRequestRepository.findById(requestId)
                .orElseThrow(() -> new FriendRequestNotFound(FRIEND_REQUEST_NOT_FOUND));

        request.setStatus(RequestStatus.REJECTED);
        friendRequestRepository.save(request);
        return new RejectFriendRequestResponse(Boolean.TRUE, FRIEND_REQUEST_REJECTED, request);

    }

    @Override
    public List<FriendRequest> getPendingRequests() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Client client = clientRepository.findByUsername(username).orElse(null);
        Teacher teacher = teacherRepository.findByUsername(username).orElse(null);

        if (client != null) {
            return friendRequestRepository.findByReceiverIdAndReceiverRoleAndStatus(
                    client.getId(), Roles.CLIENT, RequestStatus.PENDING);
        } else if (teacher != null) {
            return friendRequestRepository.findByReceiverIdAndReceiverRoleAndStatus(
                    teacher.getId(), Roles.TEACHER, RequestStatus.PENDING);}
        throw new UserNotFoundException(USER_NOT_FOUND);
    }

}
