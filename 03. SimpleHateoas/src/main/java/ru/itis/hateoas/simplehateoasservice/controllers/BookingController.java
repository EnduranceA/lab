package ru.itis.hateoas.simplehateoasservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.hateoas.simplehateoasservice.services.BookingService;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PutMapping(value = "/booking/{booking-id}/register")
    public @ResponseBody ResponseEntity<?> register(@PathVariable("booking-id") Long bookingId) {
        return ResponseEntity.ok(
                EntityModel.of(bookingService.register(bookingId)));
    }

    @PutMapping(value = "/booking/{booking-id}/send")
    public @ResponseBody ResponseEntity<?> send(@PathVariable("booking-id") Long bookingId) {
        return ResponseEntity.ok(
                EntityModel.of(bookingService.send(bookingId)));
    }

    @PutMapping(value = "/booking/{booking-id}/receive")
    public @ResponseBody ResponseEntity<?> receive(@PathVariable("booking-id") Long bookingId) {
        return ResponseEntity.ok(
                EntityModel.of(bookingService.receive(bookingId)));
    }
}
