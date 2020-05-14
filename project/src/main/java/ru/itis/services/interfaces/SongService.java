package ru.itis.services.interfaces;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.models.Song;
import ru.itis.models.User;
import java.util.List;

public interface SongService {
    Song save(MultipartFile file, User user);
    Song get(String fileName);
    List<Song> getSongs();
    void addSong(Long songId, List<Song> songs);
    List<Song> getSongsOf(User user);
}
