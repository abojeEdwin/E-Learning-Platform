package com.elearningplatform.service.PostServiceImpl;

import com.elearningplatform.data.enums.Roles;
import com.elearningplatform.data.model.Client;
import com.elearningplatform.data.model.Post;
import com.elearningplatform.data.model.Teacher;
import com.elearningplatform.data.repositories.ClientRepository;
import com.elearningplatform.data.repositories.PostRepository;
import com.elearningplatform.data.repositories.TeacherRepository;
import com.elearningplatform.dto.request.PostReq.CreatePostRequest;
import com.elearningplatform.dto.response.PostRes.CreatePostResponse;
import com.elearningplatform.exception.IllegalOperationException;
import com.elearningplatform.exception.NotAuthorizedException;
import com.elearningplatform.exception.PostNotFoundException;
import com.elearningplatform.exception.UserNotFoundException;
import com.elearningplatform.service.PostService.PostService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.elearningplatform.util.AppUtils.*;

@Service
public class PostServiceImp implements PostService {

    private PostRepository postRepository;
    private ClientRepository clientRepository;
    private TeacherRepository teacherRepository;


    @Override
    public CreatePostResponse createPost(CreatePostRequest request) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = clientRepository.findByUsername(username).orElse(null);
        Teacher teacher = null;
        if (client == null) {
            teacher = teacherRepository.findByUsername(username)
                    .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));}
        Roles userRole;
        if (client != null) {
            userRole = client.getRoles();
        } else {
            userRole = teacher.getRoles();}
        if (!(userRole.equals(Roles.TEACHER) || userRole.equals(Roles.CLIENT))) {
            throw new IllegalOperationException(ILLEGAL_OPERATION);}
        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .imageUrl(request.getImageUrl())
                .role(userRole)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        Post savedPost = postRepository.save(post);
        return mapToResponse(savedPost);
    }

    @Override
    public void deletePost(Long id) {
        Post post = getPost(id);
        postRepository.delete(post);
    }

    @Override
    public List<CreatePostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private CreatePostResponse mapToResponse(Post post) {
        return CreatePostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .role(post.getRole().name())
                .createdAt(post.getCreatedAt())
                .build();
    }

    private Post getPost(Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND));
        Client client = clientRepository.findByUsername(username).orElse(null);
        Teacher teacher = (client == null) ? teacherRepository.findByUsername(username).orElse(null) : null;
        if (client == null && teacher == null) {throw new UserNotFoundException(USER_NOT_FOUND);}
        Roles userRole = client != null ? client.getRoles() : teacher.getRoles();
        if (!post.getRole().equals(Roles.CLIENT) || !post.getRole().equals(Roles.TEACHER)&& userRole != Roles.SUPER_ADMIN || userRole != Roles.ADMIN) {
            throw new NotAuthorizedException(NOT_AUTHORIZED);}return post;}
}
