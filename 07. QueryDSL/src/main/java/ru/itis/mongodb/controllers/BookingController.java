package ru.itis.mongodb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.mongodb.models.State;
import ru.itis.mongodb.services.BookingService;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PutMapping(value = "/booking/{_id}/register")
    public @ResponseBody ResponseEntity<?> register(@PathVariable("_id") String bookingId) {
        return ResponseEntity.ok(
                EntityModel.of(bookingService.register(bookingId)));
    }

    @PutMapping(value = "/booking/{_id}/delete")
    public ResponseEntity.BodyBuilder delete(@PathVariable("_id") String bookingId) {
        bookingService.delete(bookingId);
        return ResponseEntity.status(HttpStatus.OK);
    }

    @PutMapping(value = "/booking/{state}")
    public @ResponseBody ResponseEntity<?> findByState(@PathVariable("state") State state) {
        return ResponseEntity.ok(
                EntityModel.of(bookingService.findByState(state)));
    }
}