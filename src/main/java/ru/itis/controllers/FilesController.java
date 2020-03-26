package ru.itis.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.models.FileInfo;
import ru.itis.services.FileInfoService;
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

    @RequestMapping(value = "/files", method = RequestMethod.POST)
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

    @RequestMapping(value ="/files/{file-name:.+}" , method = RequestMethod.GET)
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

    @RequestMapping(value = "/files", method = RequestMethod.GET)
    public ModelAndView getFileUploadPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("file_upload");
        return modelAndView;
    }
}