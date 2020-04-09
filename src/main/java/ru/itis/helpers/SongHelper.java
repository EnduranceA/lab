package ru.itis.helpers;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.models.Song;
import ru.itis.models.User;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@Component
public class SongHelper {

    @Value("${storage.files}")
    private String path;

    public Song convert(MultipartFile file, User user) {
        String fileName = createStorageName(file);
        return Song.builder()
                .originalFileName(file.getOriginalFilename())
                .storageFileName(fileName)
                .size(file.getSize())
                .type(file.getContentType())
                .url("http://localhost:8080/files/" + fileName)
                .author(user)
                .createdAt(LocalDate.now())
                .build();
    }

    private String createStorageName(MultipartFile file) {
        return UUID.randomUUID().toString() + "." +
                FilenameUtils.getExtension(file.getOriginalFilename()) ;
    }

    public void uploadFile(Song song, MultipartFile file) {
        try {
            //берем абсолютный путь до папки:
            String pathDir = path;
            File dir = new File(pathDir);
            //проверяем, существует ли такая папка
            if (!dir.exists()) {
                dir.mkdir();
            }
            String fullPath = pathDir + File.separator + song.getStorageFileName();
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(fullPath)));
            stream.write(bytes);
            stream.close();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }

    }
}
