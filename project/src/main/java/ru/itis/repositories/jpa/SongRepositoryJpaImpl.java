package ru.itis.repositories.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.models.Song;
import ru.itis.models.User;
import ru.itis.repositories.SongRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component(value = "songRepository")
public class SongRepositoryJpaImpl implements SongRepository {

    //language=HQL
    private final static String HQL_FIND_ALL = "FROM Song song";

    //language=HQL
    private final static String HQL_FIND_BY_NAME = "SELECT f FROM Song f WHERE f.storageFileName = ?1";

    //language=HQL
    private final static String HQL_FIND_BY_USER = "FROM Song s WHERE s.author = ?1";
    @PersistenceContext
    private EntityManager entityManagerFactory;

    @Override
    public Optional<Song> find(Long id) {
        return Optional.ofNullable(entityManagerFactory.find(Song.class, id));
    }

    @Override
    public List<Song> findAll() {
        return entityManagerFactory
                .createQuery(HQL_FIND_ALL).getResultList();
    }

    @Override
    @Transactional
    public void save(Song song) {
        entityManagerFactory.persist(song);
    }

    @Override
    public void delete(Long id) {
        entityManagerFactory.remove(id);
    }

    @Override
    public Optional<Song> findByName(String name) {
        return entityManagerFactory.createQuery(HQL_FIND_BY_NAME)
                .setParameter(1, name).getResultList().stream().findFirst();
    }

    @Override
    public List<Song> findByUserId(User user) {
        return entityManagerFactory.createQuery(HQL_FIND_BY_USER)
                .setParameter(1,user).getResultList();
    }
}