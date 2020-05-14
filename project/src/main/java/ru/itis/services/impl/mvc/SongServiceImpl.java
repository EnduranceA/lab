package ru.itis.services.impl.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.helpers.SongHelper;
import ru.itis.models.Song;
import ru.itis.models.User;
import ru.itis.repositories.SongRepository;
import ru.itis.services.interfaces.SongService;
import java.util.List;
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
        user.getSongs().add(song);
        helper.uploadFile(song, file);
        return song;
    }

    @Override
    public Song get(String fileName) {
        Optional<Song> songOptional = songRepository.findByName(fileName);
        return songOptional.orElse(null);
    }

    @Override
    public List<Song> getSongs() {
        return songRepository.findAll();
    }

    @Override
    public void addSong(Long songId, List<Song> songs) {
        Optional<Song> songOptional = songRepository.find(songId);
        if (songOptional.isPresent()) {
            Song song = songOptional.get();
            songs.add(song);
        }
    }

    @Override
    public List<Song> getSongsOf(User user) {
        return songRepository.findByUserId(user);
    }


}
