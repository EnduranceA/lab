package ru.itis.repositories;

import org.springframework.stereotype.Component;
import ru.itis.models.CookieValue;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component
public class CookieValuesRepositoryJpaImpl implements CookieValuesRepository {

    @PersistenceContext
    private EntityManager entityManagerFactory;

    //language=HQL
    private static final String HQL_FIND_ALL =  "FROM CookieValue c";

    //language=HQL
    private static final String HQL_FIND_BY_VALUE = "SELECT c FROM CookieValue c WHERE c.value= ?1";

    @Override
    public Optional<CookieValue> find(Long id) {
        return Optional.ofNullable(entityManagerFactory.find(CookieValue.class, id));
    }

    @Override
    public List<CookieValue> findAll() {
        return entityManagerFactory.createQuery(HQL_FIND_ALL).getResultList();
    }

    @Override
    public void save(CookieValue cookieValue) {
        entityManagerFactory.persist(cookieValue);
    }

    @Override
    public void delete(Long id) {
        CookieValue cookieValue = entityManagerFactory.find(CookieValue.class, id);
        entityManagerFactory.remove(cookieValue);
    }

    @Override
    public Optional<CookieValue> findByValue(String value) {
        CookieValue cookieValue = null;
        try {
            cookieValue = (CookieValue) entityManagerFactory
                    .createQuery(HQL_FIND_BY_VALUE)
                    .setParameter(1, value)
                    .getSingleResult();
        }
        catch (NoResultException nre){
            //Ignore this because the logic this is ok!
        }
        return Optional.ofNullable(cookieValue);
    }
}
