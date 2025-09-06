package com.elearningplatform.controller;


import com.elearningplatform.dto.request.PostReq.CreatePostRequest;
import com.elearningplatform.dto.response.PostRes.CreatePostResponse;
import com.elearningplatform.service.PostService.PostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.elearningplatform.util.AppUtils.POST_DELETED_SUCCESSFULLY;

@RestController
@RequestMapping("/api/v1/posts")
@AllArgsConstructor
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("create-post")
    public ResponseEntity<CreatePostResponse> createPost(@Valid @RequestBody CreatePostRequest request) {
        return ResponseEntity.ok(postService.createPost(request));
    }

    @GetMapping("get-all-posts")
    public ResponseEntity<List<CreatePostResponse>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PreAuthorize("hasAnyRole('CLIENT', 'TEACHER', 'SUPER_ADMIN')")
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok(POST_DELETED_SUCCESSFULLY);
    }

}
