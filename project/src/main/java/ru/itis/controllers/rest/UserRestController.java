package ru.itis.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dto.InformationDto;
import ru.itis.models.Song;
import ru.itis.models.User;
import ru.itis.services.interfaces.SongService;
import ru.itis.services.interfaces.UserService;
import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private SongService songService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/api/{user-id}/songs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Song> getSongsOf(@PathVariable("user-id")Long userId) {
        User user = userService.getBy(userId);
        if (userService.getBy(userId) != null) {
            return songService.getSongsOf(user);
        }
        return null;
    }

    @GetMapping(value = "/api/users/{user-id}/files/information")
    public ResponseEntity<InformationDto> getInformation(@PathVariable("user-id") Long userId) {
        InformationDto result = userService.getInformation(userId);
        return ResponseEntity.ok(result);
    }

}
