package ru.itis.repositories.jpa;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.models.Message;
import ru.itis.repositories.MessageRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Component
public class MessageRepositoryImpl implements MessageRepository {

    @PersistenceContext
    private EntityManager entityManagerFactory;

    //language=HQL
    private static final String HQL_FIND_ALL = "From Message message";

    @Override
    public Optional<Message> find(Long aLong) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public List<Message> findAll() {
        return entityManagerFactory.createQuery(HQL_FIND_ALL).getResultList();
    }

    @Override
    @Transactional
    public void save(Message message) {
        entityManagerFactory.persist(message);
    }

    @Override
    public void delete(Long aLong) {

    }
}
