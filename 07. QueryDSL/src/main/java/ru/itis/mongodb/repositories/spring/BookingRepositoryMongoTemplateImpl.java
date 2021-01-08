package ru.itis.mongodb.repositories.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.itis.mongodb.models.Booking;
import ru.itis.mongodb.repositories.BookingRepository;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class BookingRepositoryMongoTemplateImpl implements BookingRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(Booking booking) {
        mongoTemplate.save(booking);
    }

    @Override
    public void delete(String _id) {
        Query query = new Query(where("_id").is(_id));
        mongoTemplate.remove(query, Booking.class);
    }

    @Override
    public void update(Booking booking) {
        mongoTemplate.save(booking);
    }
}
