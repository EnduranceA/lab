package ru.itis.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "client")
@ToString(exclude = "messages")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "hash_password")
    private String hashPassword;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Message> messages;
}
