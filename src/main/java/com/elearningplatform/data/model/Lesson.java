package com.elearningplatform.data.model;


import com.elearningplatform.data.enums.LessonStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="table")
@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer duration;

    @Enumerated(EnumType.STRING)
    private LessonStatus status;

    private String videoCallRoomId;
    private String videoCallUrl;
    private LocalDateTime videoCallStartedAt;
    private LocalDateTime videoCallEndedAt;

}
