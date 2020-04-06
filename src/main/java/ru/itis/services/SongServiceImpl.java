package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.helpers.SongHelper;
import ru.itis.models.Song;
import ru.itis.models.User;
import ru.itis.repositories.SongRepository;
import ru.itis.repositories.UserRepository;
import ru.itis.services.interfaces.SongService;

import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    @Qualifier("songRepository")
    private SongRepository songRepository;


    @Autowired
    private SongHelper helper;

    @Override
    public Song save(MultipartFile file, User user) {
        Song song = helper.convert(file, user);
        songRepository.save(song);
        helper.uploadFile(song, file);
        return song;
    }

    @Override
    public Song get(String fileName) {
        Optional<Song> songOptional = songRepository.findByName(fileName);
        return songOptional.orElse(null);
    }

}
