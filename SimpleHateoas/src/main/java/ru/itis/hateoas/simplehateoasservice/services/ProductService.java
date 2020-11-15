package ru.itis.hateoas.simplehateoasservice.services;

import ru.itis.hateoas.simplehateoasservice.models.Product;

public interface ProductService {
    Product updatePrice(Long bookingId, Integer price);
}
