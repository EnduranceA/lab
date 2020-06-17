package ru.itis.models;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "client")
@ToString(exclude = {"songs", "sentMessages"})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "hash_password")
    private String hashPassword;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "confirm_code")
    private String confirmCode;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author", cascade=CascadeType.ALL)
    @Where(clause = "type = 'audio/mpeg'")
    private List<Song> songs = new ArrayList<>();

    //отправленные сообщения
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sender", cascade=CascadeType.ALL)
    private List<Message> sentMessages = new ArrayList<>();
//
//    //полученные сообщения
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "to", cascade=CascadeType.ALL)
//    private List<Message> receivedMessages = new ArrayList<>();
}