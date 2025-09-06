package com.elearningplatform.dto.response.PostRes;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreatePostResponse {

    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private String role;
    private LocalDateTime createdAt;

}

