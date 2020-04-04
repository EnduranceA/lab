package ru.itis.repositories;

import ru.itis.models.FileInfo;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component(value = "fileInfoRepository")
public class FileInfoRepositoryJpaImpl implements FileInfoRepository {

    //language=HQL
    private final static String HQL_FIND_ALL = "SELECT f FROM FileInfo f";

    //language=HQL
    private final static String HQL_FIND_BY_NAME = "SELECT f FROM FileInfo f WHERE f.storageFileName = ?1";

    @PersistenceContext
    private EntityManager entityManagerFactory;

    @Override
    public Optional<FileInfo> find(Long id) {
        return Optional.ofNullable(entityManagerFactory.find(FileInfo.class, id));
    }

    @Override
    public List<FileInfo> findAll() {
        return entityManagerFactory.createQuery(HQL_FIND_ALL).getResultList();
    }

    @Override
    @Transactional
    public void save(FileInfo fileInfo) {
        entityManagerFactory.persist(fileInfo);
    }

    @Override
    public void delete(Long id) {
        entityManagerFactory.remove(id);
    }

    @Override
    public Optional<FileInfo> findByName(String name) {
        return Optional.ofNullable((FileInfo)entityManagerFactory.createQuery(HQL_FIND_BY_NAME)
                .setParameter(1, name).getSingleResult());
    }
}