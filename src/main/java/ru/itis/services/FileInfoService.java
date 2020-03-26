package ru.itis.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.models.FileInfo;

public interface FileInfoService {
    FileInfo save(MultipartFile file, String email);
    FileInfo get(String fileName);
}
