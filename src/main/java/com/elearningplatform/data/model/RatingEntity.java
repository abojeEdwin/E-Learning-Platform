package com.elearningplatform.data.model;


import com.elearningplatform.data.enums.Rating;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;


@Entity
@Table(name = "ratings")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class RatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Rating rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    @JsonManagedReference
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonManagedReference
    private Client client;

    private Double rating = 0.0;

    @Column(length = 500)
    private String comment;

    @Column(nullable = false)
    private LocalDateTime createdAt;

}

