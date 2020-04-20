package ru.itis.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = "messages")
public class Room {
    @Id
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "room")
    private List<Message> messages;
}