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
    private String id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
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

    @ManyToMany(mappedBy = "clients")
    private Set<Teacher> teachers;

    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = true)
    private Status status;

    @ManyToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;

    @ManyToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Chat> chats;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
