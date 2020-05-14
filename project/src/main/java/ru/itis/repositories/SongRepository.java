package ru.itis.repositories;

import ru.itis.models.Song;
import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends CrudRepository<Long, Song> {
    Optional<Song> findByName(String fileName);
    List<Song> findByUserId(User user);
}
