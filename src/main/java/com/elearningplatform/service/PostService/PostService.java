package com.elearningplatform.service.PostService;

import com.elearningplatform.dto.request.PostReq.CreatePostRequest;
import com.elearningplatform.dto.response.PostRes.CreatePostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    CreatePostResponse createPost(CreatePostRequest request);
    void deletePost(Long id);
    List<CreatePostResponse> getAllPosts();

}
