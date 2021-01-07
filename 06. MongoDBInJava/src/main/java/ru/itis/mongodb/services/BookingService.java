package ru.itis.mongodb.services;

import ru.itis.mongodb.models.Booking;
import ru.itis.mongodb.models.State;

import java.util.List;

public interface BookingService {
    Booking register(String bookingId);
    void delete(String bookingId);
    List<Booking> findByState(State state);
}