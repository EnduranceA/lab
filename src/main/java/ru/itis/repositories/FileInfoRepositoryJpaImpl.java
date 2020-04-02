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

    @PersistenceContext
    private EntityManager entityManagerFactory;

    @Override
    public Optional<FileInfo> find(Long id) {
        return Optional.ofNullable(entityManagerFactory.find(FileInfo.class, id));
    }

    @Override
    public List<FileInfo> findAll() {
        return null;
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
        FileInfo fileInfo = (FileInfo) entityManagerFactory.createQuery("select f from FileInfo f where f.storageFileName = :name").getResultList().get(0);
        return Optional.of(fileInfo);
    }
}