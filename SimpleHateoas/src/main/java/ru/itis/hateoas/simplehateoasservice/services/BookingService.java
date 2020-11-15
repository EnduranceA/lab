package ru.itis.hateoas.simplehateoasservice.services;

import ru.itis.hateoas.simplehateoasservice.models.Booking;

public interface BookingService {
    Booking register(Long bookingId);
    Booking send(Long bookingId);
    Booking receive(Long bookingId);
}
