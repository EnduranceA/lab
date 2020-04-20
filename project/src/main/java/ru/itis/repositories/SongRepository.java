package ru.itis.repositories;

import ru.itis.models.Song;

import java.util.Optional;

public interface SongRepository extends CrudRepository<Long, Song> {
    Optional<Song> findByName(String fileName);
}
