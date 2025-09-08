package com.elearningplatform.controller;


import com.elearningplatform.data.model.Lesson;
import com.elearningplatform.dto.request.LessonReq.BookLessonRequest;
import com.elearningplatform.dto.request.LessonReq.GenerateMeetingTokenRequest;
import com.elearningplatform.service.VideoCallService.VideoCallService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/video-call")
@AllArgsConstructor
public class VideoCallController {


    @Autowired
    private VideoCallService videoCallService;


    @PostMapping("/bookLesson")
    public ResponseEntity<Lesson> bookLesson(@RequestBody @Valid BookLessonRequest request){
        return ResponseEntity.ok(videoCallService.bookLesson(request));
    }

    @PostMapping("/initiate-call/{lessonId}")
    public ResponseEntity<Lesson> initiateVideoCall (@PathVariable Long lessonId){
        return ResponseEntity.ok(videoCallService.initiateVideoCall(lessonId));
    }

    @PostMapping("/endVideoCall")
    public void endVideoCall(@PathVariable Long lessonId){
        videoCallService.endVideoCall(lessonId);
    }

    @GetMapping("/getVideoCallInfo")
    public ResponseEntity<Lesson> getVideoCallInfo(@RequestParam Long lessonId){
        return ResponseEntity.ok(videoCallService.getLessonVideoCallInfo(lessonId));
    }

    @PostMapping("/generateMeetingToken")
    public ResponseEntity<Map<String,String>> generateMeetingToken(@RequestBody @Valid GenerateMeetingTokenRequest request){
        return ResponseEntity.ok(videoCallService.generateMeetingToken(request));
    }
}
