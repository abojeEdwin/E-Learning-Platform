package com.elearningplatform.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="teacher")
@Builder
public class Teacher {

    @Id
    private String id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false)
    private Roles roles;

    @Column(nullable = true)
    private Gender gender;

    @Column(nullable = true)
    private String location;

    @Column(nullable = true)
    private Status status;

    @ManyToMany
    @JoinTable(
            name = "client",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private Set<Client> clients;
}
