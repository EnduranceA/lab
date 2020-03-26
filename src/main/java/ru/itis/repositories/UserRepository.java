package ru.itis.repositories;

import ru.itis.models.User;
import java.util.Optional;

public interface UserRepository extends CrudRepository<Long, User> {
    void confirm(String code);
    Optional<User> findByCode(String code);
    Optional<User> findByEmail(String email);
}
