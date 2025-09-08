package com.elearningplatform.service.VideoCallService;

import com.elearningplatform.data.enums.LessonStatus;
import com.elearningplatform.data.model.Client;
import com.elearningplatform.data.model.Lesson;
import com.elearningplatform.data.model.Teacher;
import com.elearningplatform.data.repositories.ClientRepository;
import com.elearningplatform.data.repositories.LessonRepository;
import com.elearningplatform.data.repositories.TeacherRepository;
import com.elearningplatform.dto.request.LessonReq.BookLessonRequest;
import com.elearningplatform.dto.request.LessonReq.GenerateMeetingTokenRequest;
import com.elearningplatform.dto.response.DailycoRes.DailyMeetingTokenResponse;
import com.elearningplatform.dto.response.DailycoRes.DailyRoomResponse;
import com.elearningplatform.exception.IllegalOperationException;
import com.elearningplatform.exception.InvalidLessonException;
import com.elearningplatform.exception.LessonNotFoundException;
import com.elearningplatform.exception.UserNotFoundException;
import com.elearningplatform.util.DailyCoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.elearningplatform.util.AppUtils.*;

@Service
@Transactional
@AllArgsConstructor
public class VideoCallService implements VideoCall {

    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private DailyCoService dailyCoService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    private final SimpMessagingTemplate messagingTemplate;


    @Override
    public Lesson bookLesson(BookLessonRequest request) {

     Client client = clientRepository.findByUsername(request.getClientUsername()).orElse(null);
     Teacher teacher = teacherRepository.findByUsername(request.getTeacherUsername()).orElse(null);
     if (client == null && teacher == null) {
         throw new UserNotFoundException(USER_NOT_FOUND);}
     LocalDateTime endTime = request.getStartTime().plusMinutes(request.getDuration());

     Lesson bookLesson = new Lesson();
     bookLesson.setClient(client);
     bookLesson.setTeacher(teacher);
     bookLesson.setDuration(request.getDuration());
     bookLesson.setStartTime(request.getStartTime());
     bookLesson.setEndTime(endTime);
     bookLesson.setStatus(LessonStatus.SCHEDULED);
     return lessonRepository.save(bookLesson);
    }

    @Override
    public Lesson initiateVideoCall(Long lessonId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new LessonNotFoundException(LESSON_NOT_FOUND));

        if (!LessonStatus.SCHEDULED.equals(lesson.getStatus())) {
            throw new InvalidLessonException(LESSON_NOT_VALID);}

        String roomName = "lesson-" + lessonId + "-" + System.currentTimeMillis();
        Map<String, Object> roomProperties = new HashMap<>();
        roomProperties.put("name", roomName);
        roomProperties.put("privacy", "private");
        roomProperties.put("properties", Map.of(
                "enable_screenshare", true,
                "enable_chat", true,
                "eject_at_room_exp", true,
                "enable_knocking", true,
                "enable_recording", "cloud"));
        DailyRoomResponse roomResponse = dailyCoService.createRoom(roomName, roomProperties)
                .block();

        lesson.setVideoCallRoomId(roomName);
        lesson.setVideoCallUrl(roomResponse.getUrl());
        lesson.setStatus(LessonStatus.IN_PROGRESS);
        lesson.setVideoCallStartedAt(LocalDateTime.now());
        lesson = lessonRepository.save(lesson);

        messagingTemplate.convertAndSend(
                "/topic/notifications/" + lesson.getClient().getId() + lesson.getTeacher().getId(),
                username + " initiated video call");
        return lesson;
    }

    @Override
    public void endVideoCall(Long lessonId) {

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new LessonNotFoundException(LESSON_NOT_FOUND));

        lesson.setStatus(LessonStatus.COMPLETED);
        lesson.setVideoCallEndedAt(LocalDateTime.now());
        lessonRepository.save(lesson);
    }

    @Override
    public Lesson getLessonVideoCallInfo(Long lessonId) {
        return lessonRepository.findById(lessonId)
                .orElseThrow(() -> new LessonNotFoundException(LESSON_NOT_FOUND));
    }

    @Override
    public Map<String, String> generateMeetingToken(GenerateMeetingTokenRequest request) {

        Lesson lesson = lessonRepository.findById(request.getLessonId())
                .orElseThrow(() -> new LessonNotFoundException(LESSON_NOT_FOUND));
        if (!lesson.getTeacher().getId().equals(request.getUserId())) {
            request.setTeacher(Boolean.FALSE);
            throw new IllegalOperationException(TEACHER_NOT_ALLOWED);}
        if (lesson.getClient().getId() != request.getUserId() ) {
            request.setTeacher(Boolean.TRUE);;
            throw new IllegalOperationException(CLIENT_NOT_ALLOWED);
        }
        Map<String, Object> tokenProperties = new HashMap<>();
        tokenProperties.put("properties", Map.of(
                "user_name", request.isTeacher() ? lesson.getTeacher().getUsername() : lesson.getClient().getUsername(),
                "user_id", request.getUserId().toString(),
                "is_teacher", request.isTeacher(),
                "room_name", lesson.getVideoCallRoomId()
        ));
        DailyMeetingTokenResponse tokenResponse = dailyCoService.createMeetingToken(
                lesson.getVideoCallRoomId(), tokenProperties).block();

        return Map.of(
                "token", tokenResponse.getToken(),
                "roomUrl", lesson.getVideoCallUrl(),
                "roomName", lesson.getVideoCallRoomId());
    }
}
