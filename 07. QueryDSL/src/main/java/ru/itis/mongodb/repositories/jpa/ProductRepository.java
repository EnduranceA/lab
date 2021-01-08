package ru.itis.mongodb.repositories.jpa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.itis.mongodb.models.Booking;
import ru.itis.mongodb.models.Product;

@Repository
public interface ProductRepository  extends MongoRepository<Product, String>{
}
