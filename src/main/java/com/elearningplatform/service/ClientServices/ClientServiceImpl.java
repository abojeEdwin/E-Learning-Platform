package com.elearningplatform.service.ClientServices;

import com.elearningplatform.data.model.Client;
import com.elearningplatform.data.model.Lesson;
import com.elearningplatform.data.repositories.ClientRepository;
import com.elearningplatform.dto.request.ClientReq.UpdateClientProfileRequest;
import com.elearningplatform.dto.request.FriendRequestReq.SendFriendRequest;
import com.elearningplatform.dto.request.LessonReq.BookLessonRequest;
import com.elearningplatform.dto.request.PostReq.CreatePostRequest;
import com.elearningplatform.dto.request.TeacherReq.RateTeacherRequest;
import com.elearningplatform.dto.response.ApiResponse;
import com.elearningplatform.dto.response.FriendRequestRes.AcceptFriendRequestResponse;
import com.elearningplatform.dto.response.FriendRequestRes.RejectFriendRequestResponse;
import com.elearningplatform.dto.response.FriendRequestRes.SendFriendRequestResponse;
import com.elearningplatform.dto.response.PostRes.CreatePostResponse;
import com.elearningplatform.dto.response.RatingRes.RateTeacherResponse;
import com.elearningplatform.exception.ClientNotFoundException;
import com.elearningplatform.service.FriendRequestServices.FriendRequestService;
import com.elearningplatform.service.PostService.PostService;
import com.elearningplatform.service.RatingService.RatingService;
import com.elearningplatform.service.VideoCallService.VideoCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.elearningplatform.util.AppUtils.*;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private VideoCall videoCallService;
    @Autowired
    private FriendRequestService friendRequestService;

    @Override
    public ApiResponse updateProfile(UpdateClientProfileRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = clientRepository.findByUsername(username).orElse(null);
        if (client == null) {throw new ClientNotFoundException(CLIENT_NOT_FOUND);}
        client.setFullName(request.getFullName());
        client.setAddress(request.getAddress());
        client.setPhone(request.getPhone());
        client.setLocation(request.getLocation());
        Client savedClient = clientRepository.save(client);
        return new ApiResponse(Boolean.TRUE, PROFILE_UPDATE_SUCCESSFULLY,savedClient);
    }

    @Override
    public CreatePostResponse createPost(CreatePostRequest request) {
       return postService.createPost(request);
    }

    @Override
    public void deletePost(Long postId) {
        postService.deletePost(postId);
    }

    @Override
    public ApiResponse viewProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = clientRepository.findByUsername(username).orElse(null);
        if (client == null) {throw new ClientNotFoundException(CLIENT_NOT_FOUND);}
        return new ApiResponse(Boolean.TRUE, PROFILE_FOUND,client);
    }

    @Override
    public RateTeacherResponse rateTeacher(RateTeacherRequest request) {
      return ratingService.rateTeacher(request);
    }

    @Override
    public Lesson bookLesson(BookLessonRequest request) {
        return videoCallService.bookLesson(request);
    }

    @Override
    public Lesson videoCall(Long lessonId) {
        return videoCallService.initiateVideoCall(lessonId);
    }

    @Override
    public SendFriendRequestResponse sendFriendRequest(SendFriendRequest request) {
        return friendRequestService.sendFriendRequest(request);
    }

    @Override
    public AcceptFriendRequestResponse acceptFriendRequest(Long requestId) {
        return friendRequestService.acceptFriendRequest(requestId);
    }

    @Override
    public RejectFriendRequestResponse declineFriendRequest(Long requestId) {
        return friendRequestService.rejectFriendRequest(requestId);
    }
}
