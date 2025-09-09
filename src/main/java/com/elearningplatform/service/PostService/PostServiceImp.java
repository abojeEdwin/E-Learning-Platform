package com.elearningplatform.service.PostService;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.elearningplatform.util.AppUtils.*;

@Service
public class PostServiceImp implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TeacherRepository teacherRepository;


    @Override
    public CreatePostResponse createPost(CreatePostRequest request) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = clientRepository.findByUsername(username).orElse(null);
        Teacher teacher = teacherRepository.findByUsername(username).orElse(null);
        if (client == null) {
            throw new UserNotFoundException(USER_NOT_FOUND);}
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
                .client(client)
                .teacher(teacher)
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

        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND));
        Client client = clientRepository.findByUsername(username).orElseThrow(() ->  new NotAuthorizedException(NOT_AUTHORIZED));
        Teacher teacher = (client == null) ? teacherRepository.findByUsername(username).orElseThrow(()-> new NotAuthorizedException(NOT_AUTHORIZED)) : null;
        if(client.getId() != post.getClient().getId() && teacher.getId() != post.getTeacher().getId()) {
            throw new NotAuthorizedException(NOT_AUTHORIZED);
        }
        if (client == null && teacher == null) {throw new UserNotFoundException(USER_NOT_FOUND);}
        Roles userRole = client != null ? client.getRoles() : teacher.getRoles();
    return post;
}
}
