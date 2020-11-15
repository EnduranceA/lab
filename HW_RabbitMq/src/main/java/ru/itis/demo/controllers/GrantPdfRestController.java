package ru.itis.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.demo.dto.GrantUserDto;
import ru.itis.demo.producers.GrantProducer;

@RestController
public class GrantPdfRestController {

    @Autowired
    public GrantProducer grantProducer;

    @Value("${grant_first.routing.key}")
    private String routingKeyGrantFirstCourse;

    @Value("${grant_all.routing.key}")
    private String routingKeyAllCourse;

    @PostMapping("/api/grant/first")
    public ResponseEntity<String> getGrantDocumentFirstCourse(@RequestBody GrantUserDto grantUserDto) {
        grantProducer.produce(grantUserDto, routingKeyGrantFirstCourse);
        return ResponseEntity.ok("Документ для получения гранта (1 курс)");
    }

    @PostMapping("/api/grant/all")
    public ResponseEntity<String> getGrantDocumentsAllCourse(@RequestBody GrantUserDto grantUserDto) {
        grantProducer.produce(grantUserDto, routingKeyAllCourse);
        return ResponseEntity.ok("Документ для получение гранта ");
    }

}
