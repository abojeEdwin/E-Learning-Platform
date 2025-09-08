package com.elearningplatform.service.VideoCallService;

import com.elearningplatform.data.model.Lesson;
import com.elearningplatform.dto.request.LessonReq.BookLessonRequest;
import com.elearningplatform.dto.request.LessonReq.GenerateMeetingTokenRequest;

import java.util.Map;

public interface VideoCall {

    Lesson bookLesson(BookLessonRequest request);
    Lesson initiateVideoCall(Long lessonId);
    void endVideoCall(Long lessonId);
    Lesson getLessonVideoCallInfo(Long lessonId);
    Map<String, String> generateMeetingToken(GenerateMeetingTokenRequest request);


}
