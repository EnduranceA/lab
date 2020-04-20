package ru.itis.repositories;

import org.springframework.stereotype.Component;
import ru.itis.models.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component
public class MessagesRepositoryJpaImpl implements MessagesRepository {

    @PersistenceContext
    private EntityManager entityManagerFactory;

    //language=HQL
    private static final String HQL_FIND_ALL =  "FROM Message m";

    @Override
    public Optional<Message> find(Long id) {
        return Optional.ofNullable(entityManagerFactory.find(Message.class, id));
    }

    @Override
    public List<Message> findAll() {
        return entityManagerFactory.createQuery(HQL_FIND_ALL).getResultList();
    }

    @Override
    public void save(Message message) {
        entityManagerFactory.persist(message);
    }

    @Override
    public void delete(Long id) {
        Message message = entityManagerFactory.find(Message.class, id);
        entityManagerFactory.remove(message);
    }
}
