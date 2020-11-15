package ru.itis.hateoas.simplehateoasservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.hateoas.simplehateoasservice.models.Booking;
import ru.itis.hateoas.simplehateoasservice.repositories.BookingRepository;

@Component
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking register(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(IllegalArgumentException::new);
        booking.register();
        bookingRepository.save(booking);
        return booking;
    }

    @Override
    public Booking send(Long bookingId) {
        //находим заказ по id
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(IllegalArgumentException::new);
        //изменить статус заказа
        booking.send();
        //сохранить заказ с измененным статусом
        bookingRepository.save(booking);
        return booking;
    }

    @Override
    public Booking receive(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(IllegalArgumentException::new);
        //изменить статус заказа
        booking.receive();
        //сохранить заказ с измененным статусом
        bookingRepository.save(booking);
        return booking;
    }

}
