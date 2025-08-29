package com.elearningplatform.data.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name="client")
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = true, length = 100)
    private String fullName;

    @Column(nullable = false)
    private Roles roles;

    @Column(nullable = true)
    private String address;

    @Column(nullable = true, length = 15)
    private String phone;

    @Column(nullable = true)
    private String location;

    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = true)
    private Status status;

    @ManyToMany
    @JoinTable(
            name = "teacher_cilent",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private Set<Teacher> teacherList;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Chat> chats;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
