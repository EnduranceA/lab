package ru.itis.hateoas.simplehateoasservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoas.simplehateoasservice.models.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
