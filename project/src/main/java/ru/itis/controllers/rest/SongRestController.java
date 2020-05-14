package ru.itis.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.itis.models.Song;
import ru.itis.services.interfaces.SongService;

import java.util.List;


@RestController
public class SongRestController {

    @Autowired
    private SongService songService;

    @GetMapping(value = "/api/music", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Song> getSongs() {
        return songService.getSongs();
    }




}
