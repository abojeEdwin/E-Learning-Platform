package com.elearningplatform.service.VideoCallService;

import com.elearningplatform.data.repositories.LessonRepository;
import com.elearningplatform.util.DailyCoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class VideoCallService {

    private LessonRepository lessonRepository;
    private DailyCoService dailyCoService;




}
