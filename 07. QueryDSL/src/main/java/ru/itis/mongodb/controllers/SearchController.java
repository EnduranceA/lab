package ru.itis.mongodb.controllers;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.mongodb.dto.BookingDto;
import ru.itis.mongodb.models.Booking;
import ru.itis.mongodb.repositories.jpa.BookingRepositoryMongoRepositoryImpl;
import ru.itis.mongodb.repositories.jpa.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class SearchController {

    @Autowired
    private BookingRepositoryMongoRepositoryImpl bookingRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/bookings/search")
    public ResponseEntity<List<BookingDto>> searchByPredicate(@QuerydslPredicate(root = Booking.class) Predicate predicate) {
        return ResponseEntity.ok(
                StreamSupport.stream(bookingRepository.findAll(predicate).spliterator(), true)
                        .map(booking ->
                                BookingDto.builder()
                                        .customerId(booking.getCustomerId())
                                        .comment(booking.getComment())
                                        .productNames(booking.getProducts().stream().map(product -> productRepository.findById(product.get_id()).get().getTitle()).collect(Collectors.toList()))
                                        .sum(booking.getSum())
                                        .state(booking.getState())
                                        .build()).collect(Collectors.toList()));
    }
}

