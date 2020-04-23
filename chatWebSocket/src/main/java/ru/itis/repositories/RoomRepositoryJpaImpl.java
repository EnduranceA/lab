package ru.itis.repositories;

import org.springframework.stereotype.Component;
import ru.itis.models.Room;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component
public class RoomRepositoryJpaImpl implements RoomRepository {

    @PersistenceContext
    private EntityManager entityManagerFactory;

    //language=HQL
    private static final String HQL_FIND_ALL =  "FROM Room r";


    @Override
    public Optional<Room> find(Long id) {
        return Optional.ofNullable(entityManagerFactory.find(Room.class, id));
    }

    @Override
    public List<Room> findAll() {
        return entityManagerFactory.createQuery(HQL_FIND_ALL).getResultList();
    }

    @Override
    public void save(Room room) {
        entityManagerFactory.persist(room);
    }

    @Override
    public void delete(Long id) {
        Room room = entityManagerFactory.find(Room.class, id);
        entityManagerFactory.remove(room);
    }
}
