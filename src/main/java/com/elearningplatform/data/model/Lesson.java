package com.elearningplatform.data.model;


import com.elearningplatform.data.enums.LessonStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name="lesson")
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    @JsonManagedReference
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonManagedReference
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
