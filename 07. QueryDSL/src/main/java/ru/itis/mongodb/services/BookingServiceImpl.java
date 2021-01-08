package ru.itis.mongodb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.mongodb.models.Booking;
import ru.itis.mongodb.models.State;
import ru.itis.mongodb.repositories.driver.BookingRepositoryMongoDriverImpl;
import ru.itis.mongodb.repositories.jpa.BookingRepositoryMongoRepositoryImpl;
import ru.itis.mongodb.repositories.spring.BookingRepositoryMongoTemplateImpl;

import java.util.List;

@Component
public class BookingServiceImpl implements BookingService {

    private BookingRepositoryMongoDriverImpl bookingRepositoryDriver;
    private BookingRepositoryMongoTemplateImpl bookingRepositoryMongoTemplate;

    @Autowired
    private BookingRepositoryMongoRepositoryImpl bookingRepositoryMongoRepository;

    @Override
    public Booking register(String bookingId) {
        Booking booking = bookingRepositoryDriver.findById(bookingId);
        booking.register();
        bookingRepositoryDriver.save(booking);
        return booking;
    }

    @Override
    public void delete(String bookingId) {
        bookingRepositoryMongoTemplate.delete(bookingId);
    }

    @Override
    public List<Booking> findByState(State state) {
        return bookingRepositoryMongoRepository.findBookingsByState(state);
    }

}