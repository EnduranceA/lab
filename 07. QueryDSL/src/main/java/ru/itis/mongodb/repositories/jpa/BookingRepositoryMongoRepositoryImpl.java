package ru.itis.mongodb.repositories.jpa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import ru.itis.mongodb.models.Booking;
import ru.itis.mongodb.models.State;
import java.util.List;

@Component
public interface BookingRepositoryMongoRepositoryImpl extends MongoRepository<Booking, String>, QuerydslPredicateExecutor<Booking> {
    @Query(value = "{state:  [{state: ?state}]")
    List<Booking> findBookingsByState(@Param("state") State state);
}
