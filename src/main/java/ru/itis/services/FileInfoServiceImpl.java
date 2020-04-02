package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.helpers.FileInfoHelper;
import ru.itis.models.FileInfo;
import ru.itis.repositories.FileInfoRepository;
import java.util.Optional;

@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Autowired
    @Qualifier("fileInfoRepository")
    private FileInfoRepository fileRepository;

    @Autowired
    private FileInfoHelper helper;

    @Override
    public FileInfo save(MultipartFile file, String email) {
        FileInfo fileInfo = helper.convert(file);
        fileRepository.save(fileInfo);
        helper.uploadFile(fileInfo, file);
        return fileInfo;
    }

    @Override
    public FileInfo get(String fileName) {
        Optional<FileInfo> fileInfoOptional = fileRepository.findByName(fileName);
        return fileInfoOptional.orElse(null);
    }

}
