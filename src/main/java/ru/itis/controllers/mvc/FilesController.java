package ru.itis.controllers.mvc;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.models.FileInfo;
import ru.itis.services.interfaces.FileInfoService;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class FilesController {

    @Autowired
    private Environment environment;

    @Autowired
    private FileInfoService fileService;

    @PostMapping("/files")
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile multipartFile,
                                   @RequestParam("email") String email) {
        ModelAndView modelAndView = new ModelAndView();
        //проверяем, что файл не пустой
        if (!multipartFile.isEmpty()) {
           fileService.save(multipartFile, email);
        }
        modelAndView.setViewName("file_upload");
        return modelAndView;
    }

    @GetMapping("/files/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName,
                                HttpServletResponse response) {
        FileInfo fileInfo = fileService.get(fileName);
        response.setContentType(fileInfo.getType());
        String path = environment.getProperty("storage.files");
        try {
            InputStream is = new FileInputStream(new File(path + "\\" + fileInfo.getStorageFileName()));
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

   @GetMapping("/files")
    public ModelAndView getFileUploadPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("file_upload");
        return modelAndView;
    }
}