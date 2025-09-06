package com.elearningplatform.service.VideoCallService;

import com.elearningplatform.data.model.Lesson;

import java.util.Map;

public interface VideoCall {

    Lesson initiateVideoCall(Long lessonId);
    void endVideoCall(Long lessonId);
    Lesson getLessonVideoCallInfo(Long lessonId);
    public Map<String, String> generateMeetingToken(Long lessonId, Long userId, boolean isTeacher);
}
