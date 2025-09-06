package com.elearningplatform.service.PostService;

import com.elearningplatform.dto.request.PostReq.CreatePostRequest;
import com.elearningplatform.dto.response.PostRes.CreatePostResponse;

import java.util.List;

public interface PostService {

    CreatePostResponse createPost(CreatePostRequest request);
    void deletePost(Long id);
    List<CreatePostResponse> getAllPosts();

}
