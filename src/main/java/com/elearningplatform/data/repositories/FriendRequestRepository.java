package com.elearningplatform.data.repositories;

import com.elearningplatform.data.enums.RequestStatus;
import com.elearningplatform.data.enums.Roles;
import com.elearningplatform.data.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRequestRepository extends JpaRepository <FriendRequest, Long>{

    Optional<FriendRequest> findBySenderAndReceiverIdAndRoles(Long senderId, Long receiverId, Roles senderType, Roles receiverType);
    List<FriendRequest> findByReceiverIdAndReceiverRoleAndStatus(Long receiverId, Roles receiverType, RequestStatus status);

}
