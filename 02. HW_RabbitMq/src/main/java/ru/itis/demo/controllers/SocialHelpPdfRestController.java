package ru.itis.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.demo.dto.SocialUserDto;
import ru.itis.demo.producers.SocialProducer;

@RestController
public class SocialHelpPdfRestController {

    @Autowired
    private SocialProducer socialProducer;

    @PostMapping("/api/social")
    public ResponseEntity<String> getDocumentForMaterialHelp(@RequestBody SocialUserDto socialUserDto) {
        socialProducer.produce(socialUserDto);
        return ResponseEntity.ok("Документы для получения соц. помощи");
    }

}
