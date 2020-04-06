package ru.itis.services.interfaces;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.models.Song;
import ru.itis.models.User;

public interface SongService {
    Song save(MultipartFile file, User user);
    Song get(String fileName);
}
