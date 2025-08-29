package com.elearningplatform.data.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Table(name="post")
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    private String id;
}
