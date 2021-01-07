package ru.itis.hateoas.simplehateoasservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoas.simplehateoasservice.models.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
