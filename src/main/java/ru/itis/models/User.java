package ru.itis.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "client")
@ToString(exclude = "role")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String hashPassword;
    private Role role;
    private State state;
    private LocalDateTime createdAt;
    private String confirmCode;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author")
    private List<Song> songs = new ArrayList<>();
}