package ru.itis.repositories.jpa;

import org.springframework.stereotype.Component;
import ru.itis.models.State;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component(value = "userRepository")
public class UserRepositoryJpaImpl implements UserRepository {

    //language=HQL
    private final static String HQL_FIND_ALL = "SELECT u FROM User u";

    //language=HQL
    private final static String HQL_FIND_BY_EMAIL = "SELECT u FROM User u WHERE u.email = ?1";

    //language=HQL
    private final static String HQL_FIND_BY_CODE = "SELECT u FROM User u WHERE u.confirmCode = ?1";


    @PersistenceContext
    private EntityManager entityManagerFactory;


    @Override
    @Transactional
    public void confirm(String code) {
        User user = (User)entityManagerFactory.createQuery(HQL_FIND_BY_CODE)
                .setParameter(1, code).getSingleResult();
        user.setState(State.CONFIRMED);
        entityManagerFactory.merge(user);
    }

    @Override
    public boolean isConfirmed(String code) {
        User user = (User)entityManagerFactory.createQuery(HQL_FIND_BY_CODE)
                .setParameter(1, code).getSingleResult();
        return user.getState().equals(State.CONFIRMED);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = null;
        try {
             user = (User) entityManagerFactory
                    .createQuery(HQL_FIND_BY_EMAIL)
                    .setParameter(1, email)
                    .getSingleResult();
        }
        catch (NoResultException nre){
            //Ignore this because the logic this is ok!
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> find(Long id) {
        return Optional.ofNullable(entityManagerFactory.find(User.class, id));
    }

    @Override
    public List<User> findAll() {
        return entityManagerFactory.createQuery(HQL_FIND_ALL).getResultList();
    }

    @Override
    public void save(User user) {
        entityManagerFactory.persist(user);
    }

    @Override
    public void delete(Long id) {
        User user = entityManagerFactory.find(User.class, id);
        entityManagerFactory.remove(user);
    }
}
