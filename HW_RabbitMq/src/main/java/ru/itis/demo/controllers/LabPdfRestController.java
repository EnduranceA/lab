package ru.itis.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.demo.dto.LabUserDto;
import ru.itis.demo.producers.LabProducer;

@RestController
public class LabPdfRestController {

    @Autowired
    public LabProducer labProducer;

    @Value("${enrollment.binding.key}")
    private String routingKeyEnrollment;

    @Value("${deduction.binding.key}")
    private String routingKeyDeduction;

    @PostMapping("/api/lab/deduction")
    public ResponseEntity<String> getDocumentForDeductionLab(@RequestBody LabUserDto labUserDto) {
        labProducer.produce(labUserDto, routingKeyDeduction);
        return ResponseEntity.ok("Документ для лаборатории");
    }

    @PostMapping("/api/lab/enrollment")
    public ResponseEntity<String> getDocumentForEnrollmentLab(@RequestBody LabUserDto labUserDto) {
        labProducer.produce(labUserDto, routingKeyEnrollment);
        return ResponseEntity.ok("Документ для лаборатории");
    }


}
