package com.elearningplatform.dto.request.LessonReq;

import com.elearningplatform.data.enums.LessonStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class BookLessonRequest {

    @NotNull(message="This field is required")
    private LocalDateTime startTime;
    @NotNull(message = "This field is required")
    private LocalDateTime endTime;
    @NotNull(message="This field is required")
    private Integer duration;
    @NotNull(message = "This field is required")
    private LessonStatus status;
    @NotNull(message = "This field is required")
    private String teacherUsername;
    @NotNull(message = "This field is required")
    private String clientUsername;
}
