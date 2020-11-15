package ru.itis.hateoas.simplehateoasservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoas.simplehateoasservice.models.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
