package com.elearningplatform.dto.request.LessonReq;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateMeetingTokenRequest {

    Long lessonId;
    Long userId;
    boolean isTeacher =  Boolean.FALSE;
}
