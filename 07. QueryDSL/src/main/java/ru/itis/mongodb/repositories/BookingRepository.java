package ru.itis.mongodb.repositories;

import ru.itis.mongodb.models.Booking;

public interface BookingRepository {
    void save(Booking booking);
    void delete(String _id);
    void update(Booking booking);
}
