package ru.itis.controllers.mvc;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.models.Song;
import ru.itis.models.User;
import ru.itis.security.jwt.details.UserDetailsImpl;
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

    @GetMapping("/music")
    public String getSongPage(Model model) {
        model.addAttribute("songs", songService.getSongs());
        return "song";
    }

    @PostMapping("/song")
    @PreAuthorize("isAuthenticated()")
    public void addSongToMyMusic(Long songId) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        songService.addSong(songId, user.getSongs());
    }
}