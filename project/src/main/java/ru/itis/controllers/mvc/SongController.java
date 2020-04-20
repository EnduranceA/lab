package ru.itis.controllers.mvc;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.models.Song;
import ru.itis.services.interfaces.SongService;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class SongController {

    @Autowired
    private Environment environment;
    @Autowired
    private SongService songService;

    @GetMapping("/files/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName,
                                HttpServletResponse response) {
        Song song = songService.get(fileName);
        response.setContentType(song.getType());
        String path = environment.getProperty("storage.files");
        try {
            InputStream is = new FileInputStream(new File(path + "\\" + song.getStorageFileName()));
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @GetMapping("/songs")
    public ModelAndView getSongPage() {
        ModelAndView modelAndView = new ModelAndView("song");
        modelAndView.addObject("songs", songService.getSongs());
        return modelAndView;
    }
}