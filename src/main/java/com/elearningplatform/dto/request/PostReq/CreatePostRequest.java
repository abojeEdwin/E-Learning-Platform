package com.elearningplatform.dto.request.PostReq;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequest {

    @NotNull(message="This field is required")
    private String title;
    @NotNull(message="This field is required")
    private String content;

    private String imageUrl;

}
